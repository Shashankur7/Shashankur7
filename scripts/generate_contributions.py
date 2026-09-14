import json
import os
import urllib.request

LOGIN = "Shashankur7"
TOKEN = os.environ["GITHUB_TOKEN"]

query = '''query($login:String!) { user(login:$login) { contributionsCollection { contributionCalendar { totalContributions weeks { contributionDays { date contributionCount color } } } } } }'''
payload = json.dumps({"query": query, "variables": {"login": LOGIN}}).encode()
request = urllib.request.Request(
    "https://api.github.com/graphql",
    data=payload,
    headers={
        "Authorization": f"bearer {TOKEN}",
        "Content-Type": "application/json",
        "User-Agent": "Shashankur7-profile"
    },
)
with urllib.request.urlopen(request) as response:
    data = json.load(response)

calendar = data["data"]["user"]["contributionsCollection"]["contributionCalendar"]
weeks = calendar["weeks"]

cell = 13
gap = 3
left = 36
top = 54
width = left * 2 + len(weeks) * (cell + gap)
height = 142

svg = [f'''<svg xmlns="http://www.w3.org/2000/svg" width="{width}" height="{height}" viewBox="0 0 {width} {height}">''']
svg.append('''<rect width="100%" height="100%" rx="14" fill="#050a11" stroke="#20354d"/>''')
svg.append(f'<text x="{left}" y="27" fill="#c8d5e3" font-family="monospace" font-size="13">GitHub contributions · {calendar["totalContributions"]} in the last year</text>')

for x, week in enumerate(weeks):
    for y, day in enumerate(week["contributionDays"]):
        color = day["color"] or "#161b22"
        px = left + x * (cell + gap)
        py = top + y * (cell + gap)
        svg.append(f'<rect x="{px}" y="{py}" width="{cell}" height="{cell}" rx="2" fill="{color}"><title>{day["date"]}: {day["contributionCount"]} contributions</title><animate attributeName="opacity" values="0.45;1;0.45" dur="4s" begin="{(x + y) * 0.03:.2f}s" repeatCount="indefinite"/></rect>')

svg.append('<text x="36" y="135" fill="#6cf0a5" font-family="monospace" font-size="10">LESS</text>')
svg.append(f'<text x="{width-70}" y="135" fill="#6cf0a5" font-family="monospace" font-size="10">MORE</text>')
svg.append("</svg>")

os.makedirs("assets", exist_ok=True)
with open("assets/contributions.svg", "w", encoding="utf-8") as file:
    file.write("\n".join(svg))
