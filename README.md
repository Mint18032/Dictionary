# Dictionary_Graphic
---

## Developers
* [Ngoc Minh](https://github.com/Mint18032)
* [Viet Khanh](https://github.com/khanhvankhanh141)

## Description
A user-friendly dictionary application.

## Setup

### Setup Environment
* Download MySQL Installer [MySQL](https://dev.mysql.com/downloads) to setup MySQL Server and Workbench (this [video](https://www.youtube.com/watch?v=OM4aZJW_Ojs) might help)
* Download Javafx [Javafx](https://openjfx.io/openjfx-docs/#introduction) to setup Javafx library(this [video](https://www.youtube.com/watch?v=9XJicRt_FaI) might help from 8:15 to 12:13)
* Reload the maven library before run the project.
### Setup Project
* Open MySQL Workbench and run the SQL script in resources/dictionary.sql to import database (follow this [video](https://youtu.be/uyP46E0UA9I?t=248) from 4:08 to 6:00)
* In DictionaryManager.java, change the password and url of the database to yours to connect to the database.
* Open project with IntelliJ IDEA, using JDK 11 or later. 
* Add mysql-connector-java-8.0.26.jar, bin and lib files of freetts-1.2 in the project's lib directory if they were not automatically added.

## How to use

### Lookup
This is the main function of the app which is to look up the meaning of words.

Type the word in the textfield then click "Search" button or click to choose one of the words in our suggestion list. 
The word's definition should appears below the suggestion list.

If the searching word does not exist in our database, an alert will appear to tell you try our online searching function. 
After clicking "OK" the app will automatically close the alert and switch to the next tab, the word and its definition will present there.
You can click "Cancel" or close the alert window to skip this. 

### Translate pharagraph 
Online searching using Google API. 
Click on the tab called "Pharagraph" to use this function.

Click "Search" after typing word/sentence/paragraph in the first text field.

You can change language target (Vietnamese or English) by clicking the "VI_EN" or "EN-Vi" button.

### Add word
This function together with fix and delete word are located in "Custom" tab.

Click "Add word", a small window will appear. 
Type a word in the first textfield and its meaning in the other field then click "Add".

The label will tell you if the adding process is success or not. 
In paticular:
* "The word is successfully added to dictionary!" means you can search it in you dictionary from now on.
* "Please type your word!" will be presented if you did not fill the first textfield.
* "Please type new explanation!" when you did not type the meaning.
* If it tells you "This word is already exist!", you can try next function.

### Fix word
You can fix an existing word's defition by this function.

Click "Fix word", a small window will appear.
Type a word in the first textfield and its meaning in the other field then click "Fix" and wait for a few seconds.

The label will tell you if the process is success or not. 

### Delete word
You can delete an existing word by this function.

Click "Delete word", a small window will appear.
Type a word in the textfield then click "Delete".

The label will tell you if the process is success or not.

## Captures
![search-clicked](https://user-images.githubusercontent.com/79392486/137505209-4a909829-247d-428c-ac60-c6a9e56ff7b6.png)
![googleAPI-VI-EN](https://user-images.githubusercontent.com/79392486/137505432-c16d93cd-ebf2-4c2e-98b8-ce165746c200.png)
![custom-tab](https://user-images.githubusercontent.com/79392486/137505503-4bd20886-75ed-43c8-91aa-5136682b252b.png)

