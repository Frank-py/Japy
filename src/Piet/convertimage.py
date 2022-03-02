#Richard Bruneau, 2015
#omnigatherum.ca

from PIL import Image
import sys
import numpy
import time

maxDimArray = [150]

piet_colours = [(255, 192, 192),(255, 255, 192),(192, 255, 192),(192, 255, 255),(192, 192, 255),(255, 192, 255),(255, 0, 0),(255, 255, 0),(0, 255, 0),(0, 255, 255),(0, 0, 255),(255, 0, 255),(192, 0, 0),(192, 192, 0),(0, 192, 0),(0, 192, 192),(0, 0, 192),(192, 0, 192),(255, 255, 255),(0, 0, 0)]

filename = sys.argv[1]

#finds closest colour based on Euclidean distance
def get_nearest_colour(b):
    min_dist = float("inf")
    closest_colour = None
    for a in piet_colours:
        dist = numpy.sqrt(sum(map(lambda a,b: (a-b)**2, a, b)))
        if( dist < min_dist ):
            min_dist = dist
            closest_colour = a
    return closest_colour

def convert_image(px,w,h):
    for x in range(0,w):
        for y in range(0,h):
            px[x,y] = get_nearest_colour( px[x,y] )

#resizes image such that the longest dimension == maxLength
#then, convert colours
def process_image(maxLength, img):
    maxDim = 0 #0 or 1, width or height
    maxDims = [0,0]
    if(img.size[0] < img.size[1]): maxDim = 1
    maxLength_percent = (maxLength/float(img.size[maxDim]))
    maxDims[maxDim] = maxLength
    maxDims[1-maxDim] = int((float(img.size[1-maxDim])*float(maxLength_percent)))
    img = img.resize((maxDims[0], maxDims[1]), Image.ANTIALIAS)
    px = img.load()
    convert_image(px,img.size[0],img.size[1])
    img.save( str(filename[:-4]) + "-conv-" + str(maxLength) + ".png", "PNG" )
    
start_time = time.time()

#opens the image, and converts immediately from RGBA to RGB
img = Image.open( str(filename) ).convert("RGB")

#img.size[0] is the width of the image
#img.size[1] is the height of the image
print("Format: " + str(img.format) )
print("Mode: " + str(img.mode) )
print("Info: " + str(img.info) )
print("Width: " + str(img.size[0]) )
print("Height: " + str(img.size[1]) )

for length in maxDimArray:
    process_image(length, img)

img.close()

print("--- %s seconds ---" % (time.time() - start_time))

print("End Of Processing.")