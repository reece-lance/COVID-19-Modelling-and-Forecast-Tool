"""
This file enables you to programmatically discover what the api has to offer.

If your query exceeds 2500 results, pagination will cut it off. Do not worry,
if you really need more than 2500 results, I can implement it in our app, just
use this as a discovery tool.
"""
# You will need to install these if you do not have them already
from json import dumps
from requests import get
from urllib.parse import urlencode
import json
import os.path
import sys

# FILTERS----------------------------------------------------------------------
DATE = "2021-02-22" #YYYY-MM-DD

# areaType metric are case-sensitive.

# ltla      - Lower-tier local authority data
# nation    - Nation data (England, Northern Ireland, Scotland, and Wales)
# nhsRegion - NHS Region data
# overview  - Overview data for the United Kingdom
# region    - Region data
# utla      - Upper-tier local authority data
AREA_TYPE = "region"


# AREA_NAME = "england" # Example of filtering by authorised metric (see end of file))

# Include the filters above.
filters = [
    f"areaType={ AREA_TYPE }",
    # f"areaName={ AREA_NAME }",
    f"date={ DATE }"
]

# STRUCTURE--------------------------------------------------------------------

# Structure is flexible, you specify the property names as well as the object
# layout and then assign it string name value of the propert you want it to
# ocupy.

# For all possible authorised metrics, see the end of file.

# Renaming is possible. For example if you wanted 'areaName' returned as
# 'name', as the sample below shows, you just say:
# {
#   ...
# "name" : "areaName"
#   ...
# }

structure = {
    "date": "date",
    "name": "areaName",
    "code": "areaCode",
    "cases": {
        "daily": "newCasesByPublishDate",
        "cumulative": "cumCasesByPublishDate"
    },
    "deaths": {
        "daily": "newDeathsByDeathDate",
        "cumulative": "cumDeathsByDeathDate"
    },
    "maleCases": "maleCases",
    "femaleCases": "femaleCases",
    "hospitalCases": "hospitalCases",
    "newAdmissions": "newAdmissions",
    "cumAdmissions": "cumAdmissions"

}


# You do not care about this, does its own stuff, just execute it already.
# PUTTING IT ALL TOGETHER------------------------------------------------------

RESULT_NAME = os.path.splitext(os.path.basename(__file__))[0]
RESULT_DIR = './responses'
ENDPOINT = "https://api.coronavirus.data.gov.uk/v1/data"

api_params = {
    "filters": str.join(";", filters),
    "structure": dumps(structure, separators=(",", ":"))
}
encoded_params = urlencode(api_params)

response = get(ENDPOINT, params=api_params, timeout=10)

print(f'STATUS: {response.status_code}')
if response.status_code >= 400:
    raise RuntimeError(f'Request failed: { response.text }')

pretty_json_response = json.dumps(response.json(), indent=4, sort_keys=True);

output_file_parts = [RESULT_NAME, str.join("_", filters) + '.json']
outputFile = str.join('-', (str(s) for s in output_file_parts))

if os.path.exists(RESULT_DIR) is False:
    os.mkdir(RESULT_DIR)

with open(os.path.join(RESULT_DIR, outputFile), 'w') as f:
    f.writelines(pretty_json_response)
    f.write('\n')
    f.write(response.url)
    f.write('\n')
    f.write(f"/v1/data?{ encoded_params }")
    f.write('\n')

# AUTHORISED METRICS ----------------------------------------------------------
#		Area type as string
#	"areaType"

#		Area name as string
#	"areaName"

#		Area Code as string
#	"areaCode"

#		Date as string [YYYY-MM-DD]
#	"date"

#		Unique ID as string
#	"hash"

#		New cases by publish date
#	"newCasesByPublishDate"

#		Cumulative cases by publish date
#	"cumCasesByPublishDate"

#		Rate of cumulative cases by publish date per 100k resident population
#	"cumCasesBySpecimenDateRate"

#		New cases by specimen date
#	"newCasesBySpecimenDate"

#		Rate of cumulative cases by specimen date per 100k resident population
#	"cumCasesBySpecimenDateRate"

#		Cumulative cases by specimen date
#	"cumCasesBySpecimenDate"

#		Male cases (by age)
#	"maleCases"

#		Female cases (by age)
#	"femaleCases"

#		New pillar one tests by publish date
#	"newPillarOneTestsByPublishDate"

#		Cumulative pillar one tests by publish date
#	"cumPillarOneTestsByPublishDate"

#		New pillar two tests by publish date
#	"newPillarTwoTestsByPublishDate"

#		Cumulative pillar two tests by publish date
#	"cumPillarTwoTestsByPublishDate"

#		New pillar three tests by publish date
#	"newPillarThreeTestsByPublishDate"

#		Cumulative pillar three tests by publish date
#	"cumPillarThreeTestsByPublishDate"

#		New pillar four tests by publish date
#	"newPillarFourTestsByPublishDate"

#		Cumulative pillar four tests by publish date
#	"cumPillarFourTestsByPublishDate"

#		New admissions
#	"newAdmissions"

#		Cumulative number of admissions
#	"cumAdmissions"

#		Cumulative admissions by age
#	"cumAdmissionsByAge"

#		Cumulative tests by publish date
#	"cumTestsByPublishDate"

#		New tests by publish date
#	"newTestsByPublishDate"

#		COVID-19 occupied beds with mechanical ventilators
#	"covidOccupiedMVBeds"

#		Hospital cases
#	"hospitalCases"

#		Planned capacity by publish date
#	"plannedCapacityByPublishDate"

#		Deaths within 28 days of positive test
#	"newDeaths28DaysByPublishDate"

#		Cumulative deaths within 28 days of positive test
#	"cumDeaths28DaysByPublishDate"

#		Rate of cumulative deaths within 28 days of positive test per 100k resident population
#	"cumDeaths28DaysByPublishDateRate"

#		Deaths within 28 days of positive test by death date
#	"newDeaths28DaysByDeathDate"

#		Cumulative deaths within 28 days of positive test by death date
#	"cumDeaths28DaysByDeathDate"

#		Rate of cumulative deaths within 28 days of positive test by death date per 100k resident population
#	"cumDeaths28DaysByDeathDateRate"