#This was used for experimentation

# Custom settings file
import settings

import pandas as pd
import geopandas as gpd
import matplotlib.pyplot as plt
# import geoplot
import adjustText as aT
from geocube.api.core import make_geocube
from rasterio import features
# from os.path import isfile, join
from os import listdir, path, mkdir

import random
#nice
random.seed(69)

def get_files(directory):
    return [path.join(directory, f) for f in listdir(directory) if path.isfile(path.join(directory, f))]

def convert_geo_json_to_graph(source_folder, dump_destination):

    if path.exists(dump_destination) is False:
        mkdir(dump_destination)

    geo_jsons = get_files(source_folder)
    # print(features.is_valid_geom(geo_jsons[0]))

    print('Reading {file_count} files...'.format(file_count = len(geo_jsons)))
    areas = [gpd.read_file(f) for f in geo_jsons]

    # for a in areas:
    #     print(a.head())

    # geoplot.polyplot(
    #     world, projection=geoplot.crs.Orthographic(), figsize=(8, 4)
    # )

    # fig, ax = plt.subplots(1, 1)
    # areas[0].plot(column='areachect', ax=ax, legend=True)

    combinedArea = areas[0].copy()
    if settings.DIAGNOSTICS:
        print("Columns: ")
        for col in combinedArea.columns: 
            print(col, sep= ' ') 
        print()

    print("Combining {area_count} areas".format(area_count = len(areas)))
    combinedArea = pd.concat([x for x in areas]).set_geometry("geometry")
    # for i in range(1, len(areas)):
        # combinedArea = combinedArea.append(areas[i])
        # combinedArea = combinedArea.append(areas[i])
    number_of_colors = len(areas)
    color = ["#"+''.join([random.choice('0123456789ABCDEF') for j in range(6)])
             for i in range(number_of_colors)]

    if settings.DIAGNOSTICS:
        for i in range(len(areas)):
            print(features.is_valid_geom(areas[i]))

            # print('area={area}, name={name}, color={color}, crs = {crs}'.format(area = areas[i].at[0, 'sort_order'], name = areas[i].at[0, 'name'], color= color[i], crs= areas[i].crs))
            print({"area": areas[i].at[0, 'sort_order'], "name": areas[i].at[0, 'name'], "color": color[i], "crs": areas[i].crs.to_epsg()})
            # print('so={code}'.format(code = areas[i].at[0, 'name']))
        print()
        print("Color pallete:")
        print(''.join([c + ' ' for c in color]))
        print()

    # print('properties')
    # for a in areas:
    #     print(a['name'])
    #     for col in combinedArea.columns: 
    #         print(col, sep= ' ') 
        # for p in a['properties']:
        #     print(p, sep= ' ') 
        # print()
    print('Plotting...')
    print(combinedArea.head())
    # fig, ax = plt.subplots(1, 1)
    # # ax.get_legend().remove()
    # combinedArea.plot(column='areachect', ax=ax, legend=False)
    # # combinedArea.plot(column='areachect', ax=ax, legend=True)
    # plt.savefig('world.png')
    # plt.clf()

    # combinedArea.plot(legend = None)
    # plt.savefig('world-plain.png')
    # plt.clf()
    # plt.legend('',frameon=False)

    # combinedArea.boundary.plot()
    # Either color or column='sort_order',

    # ['#C62828', '#C62828', '#283593', '#FF9800', '#283593', '#B036C6', '#EF4957', '#DABACB', '#96D266']
    # combinedArea.plot(figsize=(108, 192))
    # combinedArea.plot(color=color)
    
    combinedArea['measure'] = [x*10 for x in range(len(areas))]

    if(settings.DIAGNOSTICS and settings.DIAGNOSTIC_LABELS):
        combinedArea['center'] = combinedArea["geometry"].representative_point()
        combinedArea_points = combinedArea.copy()
        combinedArea_points.set_geometry("center", inplace = True)

    ax = combinedArea.plot(figsize=(108, 192), color=color, rasterized=True, edgecolor = None)

    if(settings.DIAGNOSTICS and settings.DIAGNOSTIC_LABELS):
        texts = []
        for x, y, label in zip(combinedArea_points.geometry.x, combinedArea_points.geometry.y, combinedArea_points['name']):
            texts.append(plt.text(x, y, label, fontsize = 200))

        aT.adjust_text(texts, force_points=0.3, force_text=0.8, expand_points=(1,1), expand_text=(1,1), 
                    arrowprops=dict(arrowstyle="-", color='brown', lw=0.5))

        # for idx, row in combinedArea.iterrows():
    #     plt.annotate(s=row['NAME'], xy=row['coords'],
    #              horizontalalignment='center')
    # 'world-boundary.png'

    destination_file_path = path.join(dump_destination, 'boundary-plot.png');

    plt.savefig(destination_file_path, dpi = 10 )
    plt.clf()

    cube = make_geocube(vector_data=combinedArea, measurements=['sort_order'], resolution=(1, -1))
    # cube.measure.rio.to_raster("lsad_num.tif")
    # cube.sort_order.rio.to_raster("raster1234.tif")
    print(cube)
    # plt.savefig('test.png', dpi = 20 )
    # plt.clf()
    # plt.show()
    # UK = gdp.
    # UK = england.append(wales).append(scotland).append(northernireland)

    # england.plot()

    # for part in uk_parts:
    #     print(part)

convert_geo_json_to_graph(settings.DATA_DESTINATION_REGIONS, settings.PLOTS_DESTINATION_REGION)
# convert_geo_json_to_graph(settings.DATA_DESTINATION_LTLA, settings.PLOTS_DESTINATION_LTLA)
# convert_geo_json_to_graph(settings.DATA_DESTINATION_UTLA, settings.PLOTS_DESTINATION_UTLA)