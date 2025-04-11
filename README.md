# FlickrPhotoApp
# Simple app for displaying photos from Flickr
# Author: Benjamin Dempsey

# Development Notes:
- I did not implement pagination while loading the images, however I am using the Coil library to load images which handles loading images as the user scrolls
- The Coil library also handles caching of loaded images for me
- Error handling is with a simple try/catch block. In production this should instead be able to handle different kinds of errors.
- Image Details screen occasionally causes the app to crash, this is because the compose navigation improperly handles the url provided by Flickr
- In production the API key should come from a local file that is not uploaded to the repository