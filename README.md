# intruder-detection-system
### Problem Description

Intruder Detection system is used to monitor the trespassers on a continuous basis by the owners to determine the people approaching their private areas where valuable items are placed, based on that the owner can take the necessary steps to mitigate the consequences. Our Intruder detection system has three levels of security namely :

1. Surveillance area : This area is where our Passive Infrared Sensors can detect any intruder and send an alert to the end user. Our camera unit captures the image of an object and sends the captured images too to the end user. The PIR sensors can range up to 10 meters and alert the end user if any intruder is within this range.

2. Proximity area : This is where our distance sensors can detect the distance of the intruder coming. The distance sensors can detect anything up to 7 meter of distance.

3. Threshold area : It is when our distance controller calculates the distance of the intruder by checking if that intruder is within 4 meters. If yes, the distance sensor triggers an alarm event by sending the information to the alarm controller. On top of that it sends an event of performing the casing of the area. Thus preventing the intruder from coming into the private space or breaking in.

### Visual Representation of three level security of the system:
Below is the visual representation of our three level security.You can observe the placement of sensors,their ranges and so on.

![visual representation](/system-pictorial-overview.png)

### High-level SRC (Synchronous Reactive Component)/ Block Diagram:
With the help of formal modelling methods, you can observe the high level overview of complete synchronous reactive component for this system.

![SRC-high-level](/SRC-high-level.png)

### Extended state machine to understand how casing works
![esm-casing](/esm-casing-controller.png)

### How to run:
1. you must have open jdk version 16 or all the versions compatible with it should work too.
2. You may need to create the imageNotifications directory at the root level if it doesn't exist already.
3. We have added three input files in the solution repo. (named DistanceValues1.txt, DistanceValues2.txt, DistanceValues3.txt).
4. You will see the Main.java (entry class) in the package DataCollectors as path "/src/main/java/IntruderDetection/SRC/DataCollectors/Main.java". At line number 26 it reads the distance file. Update the Distance file names if required to run multiple scenarios. 
5. Build and configure the application to mark the starting point as class Main.java. Simply run the solution and you should be able to see the results on console.
6. The images can be seen in the directory imageNotifications.

### Good Luck!!!
