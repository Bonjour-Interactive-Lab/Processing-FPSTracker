# FPSTracker

P5 library for monitoring performance inspired by [stats.js](https://github.com/mrdoob/stats.js/)

| Plateforme : 	| Status:		|
|---------------|---------------|
| **`Windows`** | ![status](https://img.shields.io/badge/pre-release-green.svg?longCache=true&style=flat&colorA=grey&colorB=f48c42) |
| **`OSX`** 	| ![status](https://img.shields.io/badge/pre-release-green.svg?longCache=true&style=flat&colorA=grey&colorB=f48c42) |
| **`Linux`** 	| ![status](https://img.shields.io/badge/pre-release-green.svg?longCache=true&style=flat&colorA=grey&colorB=f48c42) |

## Features

* Track and display Frame per seconds (history to the last frame)
* Track and display Milliseconds needed to render the last frame
* Track and display MBytes of allocated memory

## Tested plateforms

* Windows :
  * Windows 10 x64, 16GO, intel i7 3.6Ghz, GPU NVidia GTX 970

## Informations and major updates

* Data tracking are set between the pre() and draw() methods. Each of then run between the main draw() function of processing. Pre() run after the beginDraw() and draw() run at the end the main draw() and before the endDraw();
* Because the tracking run between the main draw() methods the visualizer only display the frame from last computed frame to history n-x. The actual frame will be compute at the and of the draw and add for the next loop.

## Architecture et Design Pattern
![Design Pattern](https://gitlab.bonjour-lab.com/alexr4/FPSTracker/raw/master/DesignPattern.png)