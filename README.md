# hacktm2017
Driver Authorization system using Computer Vision (**4SEC**)

## 4SEC description

Now, no one can imagine their life without a car. Moreover, a lot of us have their own cars.
The interesting part is that everyone who has car's key can drive it even without owner's knowing. This way it could be stolen.

What if every time a person tries to take someone's car, the owner would get a picture of that person and at the same time will be able to restrict to drive(bad case) it or if he knows that he will allow it (good case).

## Use cases
1. John left his car in a dangerous district for a night, at the night he instantly got a notification that someone tries to take his car and as he saw his keys were stolen from him. This way John took a pic of the thief and called the police. 🙂

2. Sara left her car home for a week with her keys. Her grandson tried to take the keys and use the car without his mother permission. Unfortunately, he couldn't drive cause Sara got a notification and saw that her son wants to take the car and she blocked it.

This project **solve stealing cars and problem and unauthorized car driving**.

## Solution
 Our solution uses a simple camera that is integrated into the car and analyzes drivers picture using computer vision from machine learning from all the list of persons that owner already gave permission to drive the car to this person. 

In case that someone tries to start the engine, the owner will get a notification from our mobile app that takes him to a screen where is shown driver person's face and buttons for allowing or refuse.

## Future plans
In future, we want that if the owner denies access, the car will stop automatically and block itself.

## Architecture
The application is made of **3 modules**: 
 - Mobile app (for owner notification and login);
 - Server ( checks if driver is trusted and send's notifications to users)
 - Car simulation module (simple camera from PC) that takes a picture and sends it to the server for processing.



## Mobile app
Android client application that receives notifications about unauthorized access into the car

## Server
NodeJS application using Express framework and Azure Faces API service for person's detection

## Simulator
Python application that takes users picture and sends it to the server


### Dependencies
Node Server
- multipart
- Express
- fetch
Android app
- Butterknife
- Glide
- Firebase
- Retrofit
Simulation Module
- numpy
- pygame
- requests
