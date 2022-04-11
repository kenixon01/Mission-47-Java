# Mission 47
Mission 47 is an interactive text-based adventure game, in which the player, a spaceship captain, must journey
47 light years to save their crew from execution by a Tilani warlord.

### Text File Data Structure Reference
This file describes, in detail, the structure of each text file located in Mission_47 project package.
This is for the developer's reference only.

NOTE: "----" functions as a delimiter to separate general from specific component properties.

#### GlobalMessages.txt
<div style="
display: grid; 
grid-template-columns: 200px 500px; ">
<div style="grid-column: 1">GAME NAME</div>
<div>Name of game</div>
<div style="grid-column: 1/span 2">----</div>
<div style="grid-column: 1">GAME INSTRUCTIONS</div>
<div>How to play game</div>
<div style="grid-column: 1/span 2">----</div>
<div style="grid-column: 1">GAME DESCRIPTION</div>            
<div>General game description</div>
<div style="grid-column: 1/span 2">----</div>
<div style="grid-column: 1">PROLOGUE</div>                    
<div>Game storyline</div>
<div style="grid-column: 1/span 2">----</div>
<div style="grid-column: 1">SPLASH SCREEN</div>               
<div>Allows player to enter "yes" or "no" to start the game</div>
<div style="grid-column: 1/span 2">----</div>
<div style="grid-column: 1">ENTER NAME SCREEN</div>           
<div>Allows player to enter their character's name</div>
</div><hr>

#### Puzzles.txt
<div style="
display: grid; 
grid-template-columns: 200px 550px; ">

<div style="grid-column: 1">PUZZLE ID</div>
<div>Puzzle identifier</div>
<div style="grid-column: 1">PUZZLE NAME</div>
<div>Name of puzzle</div>
<div style="grid-column: 1">PUZZLE DESCRIPTION</div>
<div>Description for puzzle</div>
<div style="grid-column: 1/span 2">----</div>
<div style="grid-column: 1">PUZZLE HINT</div>
<div>Brief hint for puzzle</div>
<div style="grid-column: 1">PUZZLE ANSWER</div>
<div>Answer for puzzle</div>
<div style="grid-column: 1">ROOM ID</div>
<div>Room in which the puzzle activates</div>
<div style="grid-column: 1">MAX ATTEMPTS</div>
<div>Number of attempts player has to complete puzzle before resetting when player leaves room</div>
<div style="grid-column: 1">ITEM ID</div>
<div>Item identifier for reward once puzzle is complete</div>
<div style="grid-column: 1">LOCKEDROOMID</div>
<div>Rooms that the puzzle unlocks upon solving</div>
</div><hr>


#### Items.txt
<div style="
display: grid; 
grid-template-columns: 200px 500px; ">

<div style="grid-column: 1">ITEM ID</div>
<div>Item identifier</div>
<div style="grid-column: 1">ITEM NAME</div>
<div>Name of item</div>
<div style="grid-column: 1">ITEM DESCRIPTION</div>
<div>Description for item</div>
<div style="grid-column: 1/span 2">----</div>
<div style="grid-column: 1">ROOM ID</div>
<div>Which room item is located in</div>
<div style="grid-column: 1">TRADE OUTPUT</div>
<div>What item can be traded for</div>
</div><hr>

#### Rooms.txt

<div style="
display: grid; 
grid-template-columns: 200px 500px; ">

<div style="grid-column: 1">ROOM ID</div>
<div>Room identifier</div>
<div style="grid-column: 1">ROOM NAME</div>
<div>Name of room</div>
<div style="grid-column: 1">ROOM DESCRIPTION</div>
<div>Description for room</div>
<div style="grid-column: 1 /span 2">----</div>
<div style="grid-column: 1">LOCKED/UNLOCKED</div>
<div>Either locked or unlocked</div>
<div style="grid-column: 1">NORTH #</div>
<div>Room ID located to north of room, # is blank if there is no connection</div>
<div style="grid-column: 1">SOUTH #</div>
<div>Room ID located to south of room, # is blank if there is no connection</div>
<div style="grid-column: 1">EAST #</div>
<div>Room ID located to east of room, # is blank if there is no connection</div>
<div style="grid-column: 1">WEST #</div>
<div>Room ID located to west of room, # is blank if there is no connection</div>
<div style="grid-column: 1"># # # #</div>
<div>List of any number of items in room, separated by a blank space</div>
</div><hr>

#### Trader.txt
<div style="display: grid;
grid-template-columns: 200px 500px; ">

<div style="grid-column: 1">TRADER ID</div>
<div>Trader identifier</div>
<div style="grid-column: 1">TRADER NAME</div>
<div>Name of trader</div>
<div style="grid-column: 1">TRADER DESCRIPTION</div>
<div>Description for trader</div>
<div style="grid-column: 1/span 2">----</div>
<div style="grid-column: 1">ROOM ID</div>
<div>Which room trader is located in</div>
<div style="grid-column: 1"># # # #</div>
<div>List of any number of items in trader inventory, separated by a blank space</div>
</div>

#### Monster.txt
<div style="display: grid;
grid-template-columns: 200px 500px; ">

<div style="grid-column: 1">MONSTER ID</div>
<div>Monster identifier</div>
<div style="grid-column: 1">MONSTER NAME</div>
<div>Name of monster</div>
<div style="grid-column: 1">MONSTER DESCRIPTION</div>
<div>Description for monster</div>
<div style="grid-column: 1/span 2">----</div>
<div style="grid-column: 1">ROOM ID</div>
<div>Which room monster is located in</div>
<div style="grid-column: 1">HEALTH</div>
<div>Initial health</div>
<div style="grid-column: 1">DAMAGE THRESHOLD</div>
<div>Value (max 100) at which the attack damage will double</div>
<div style="grid-column: 1">ATTACK DAMAGE</div>
<div>Initial monster attack damage</div>
<div style="grid-column: 1">DEATH MESSAGE</div>
<div>Description that appears if the monster dies</div>
</div>

## Installation
No installation required.<br>
Users can play the game using a Java IDE or their operating system's built-in terminal, such as **Windows PowerShell** 
for Windows OS users or **Terminal** for macOS users.

The user can read the user manual through a text editor, such as **Notepad** or **Notepad++**.  
An online user manual is also provided via this link: 
<a href="http://localhost:63342/Mission-47-Java/Mission_47/Website/index.html">Click here</a>.

<a href="http://localhost:63342/Mission_47/UserManual/Website/index.html?_ijt=iib8tgobql5jn06t9ojpmrvcs3">
    <img alt="User Manual" src="src/UserManual/Images/website.png" width="50%"/>
</a>

## Visuals
<img alt="Splash" src="src/Website/Media/SplashScreen.png" width="50%"/><br>
<img alt="GamePlay7" src="src/Website/Media/GamePlay7.png" width="50%"/><br>
<img alt="GamePlay8" src="src/Website/Media/GamePlay8.png" width="50%"/><br>
<img alt="GamePlay9" src="src/Website/Media/GamePlay9.png" width="50%"/><br>
<img alt="GamePlay10" src="src/Website/Media/GamePlay10.png" width="50%"/><br>
<img alt="GamePlay11" src="src/Website/Media/GamePlay11.png" width="50%"/><br>
<img alt="PuzzleCompletion" src="src/Website/Media/PuzzleCompletion.png" width="50%"/><br>
<img alt="Trader" src="src/Website/Media/Trader.png" width="50%"/><br>
<img alt="GamePlay12" src="src/Website/Media/GamePlay12.png" width="50%"/><br>
<img alt="GamePlay13" src="src/Website/Media/GamePlay13.png" width="50%"/><br>
<img alt="GamePlay14" src="src/Website/Media/GamePlay14.png" width="50%"/><br>
<img alt="GamePlay15" src="src/Website/Media/GamePlay15.png" width="50%"/><br>
<img alt="GamePlay16" src="src/Website/Media/GamePlay16.png" width="50%"/><br>
