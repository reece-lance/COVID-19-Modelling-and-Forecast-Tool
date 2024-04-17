import seaborn as sns

names = ["Blues", "flare", "cubehelix", "dark:salmon", "Spectral","icefire","rocket_r","viridis","crest"]

for n in names:
    print(n)
for n in names:
    # print(n)
    pal = sns.color_palette(n)
    colours = str(pal.as_hex()).replace('\'', '\"').replace('[', '(').replace(']', ')')
    print(colours)

# for c in pallete:
#     print(c)

# Pheela - flare
# Jdiazgar - spectral
# Gupta - icefire
# deanmak - rocket_r
# reece - viridis
# Nasar - crest

