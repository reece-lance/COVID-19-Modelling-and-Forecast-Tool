DUMP_FOLDER = "sourceData"

# Regions
REGION_RESPONSE_PATH = "responses\\UkCovidApiCalls-areaType=region_date=2021-02-22.json"

# Lower-tier local authority
LTLA_RESPONSE_PATH = "responses\\UkCovidApiCalls-areaType=ltla_date=2021-02-22.json"

# Upper-ties local authority
UTLA_RESPONSE_PATH = "responses\\UkCovidApiCalls-areaType=utla_date=2021-02-22.json"

# Destination for region data
DATA_DESTINATION_REGIONS = "..\\sourceData\\fetched_regions"
# Destination for ltla data
DATA_DESTINATION_LTLA = "..\\sourceData\\fetched_ltla"
# Destination for utla data
DATA_DESTINATION_UTLA = "..\\sourceData\\fetched_utla"

# Destinaton for region 
PLOTS_DESTINATION_REGION =  "plots\\region"
# Destinaton for region 
PLOTS_DESTINATION_LTLA =  "plots\\ltla"
# Destinaton for region 
PLOTS_DESTINATION_UTLA =  "plots\\utla"

# Find That Postcode API endpoint for getting GeoJSON data for specified area code
# example call: https://findthatpostcode.uk/areas/E14000639.geojson'''
FDPC_API_URL = "https://findthatpostcode.uk/areas/{code}.geojson"

# Data files name format
RESULT_NAME = "{code}.json"

DIAGNOSTICS = True
DIAGNOSTIC_LABELS = True