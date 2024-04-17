# Custom settings file
import settings

import json
import os
from requests import get
import time

def region_data_parser(data):
    """ Defines how to get area odes from region uk covid api response. """

    result = []
    for region in data["data"]:
        result.append(region["code"])
    return result

def ltla_data_parser(data):
    """ Defines how to get area odes from ltla uk covid api response. """

    return region_data_parser(data);

def utla_data_parser(data):
    """ Defines how to get area odes from utla uk covid api response. """

    return region_data_parser(data);

# def fetch_codes()

def get_geo_jsons(file_to_parse_path, file_parser, dump_destination):
    """ Scrapes the input file with a parser for area codes
        then proceeds to fetch GeoJSON data from FDC API,
        and stores it in dump folder."""

    if os.path.exists(dump_destination) is False:
        os.makedirs(dump_destination)

    with open(file_to_parse_path, "r") as file:
        data = json.load(file)
        parsed_response = file_parser(data)
        code_fetched_count = len(parsed_response)

        print("Fetching {count} codes...".format(count = len(parsed_response)))
        for code in parsed_response:
            print(code_fetched_count, ' ', end='')
            code_fetched_count -= 1

            destination_file_path = os.path.join(dump_destination, settings.RESULT_NAME.format(code = code));
            if os.path.exists(destination_file_path) is True:
                print("File {file_path} already exists, skipping...".format(file_path = destination_file_path))
                continue

            print("{code}...".format(code = code))
            print(settings.FDPC_API_URL.format(code = code))

            response = get(settings.FDPC_API_URL.format(code = code), timeout=10)
            if response.status_code >= 400:
                raise RuntimeError(f'Request failed: { response.text }')
            elif response.status_code >= 500:
                raise RuntimeError(f'Server error: { response.text }')
            response_data = json.dumps(response.json())           
            
            with open(destination_file_path, 'w') as output_file:
                output_file.write(str(response_data))

            time.sleep(0.5)
        

get_geo_jsons(settings.REGION_RESPONSE_PATH, region_data_parser, settings.DATA_DESTINATION_REGIONS)
get_geo_jsons(settings.LTLA_RESPONSE_PATH, ltla_data_parser, settings.DATA_DESTINATION_LTLA)
get_geo_jsons(settings.UTLA_RESPONSE_PATH, utla_data_parser, settings.DATA_DESTINATION_UTLA)
