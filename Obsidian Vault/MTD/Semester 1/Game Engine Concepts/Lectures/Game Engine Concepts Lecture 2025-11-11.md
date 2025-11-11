#game_engine_concepts #hrt_anniversary 

![[dog-rainy-dog.mp4]]


**Solution 1**  → Experiment

→ statistical analysis

**Solution 2** → Model

→ We could simulate “artificial users”

# Human Input

- for both solutions → Strategies
- Input Tasks
	- Text
	- Position
	- Selection
	- Quantify
	- etc

## Text

- Keyboard
- Gamepad 
- Knob
- Speech

### Typing Performance Measures

- Notation
	- Presented Text **P**
	- Transcribed Text **T**
	- Correct **C**

- Entry Rates
	- Completion Time
	- Keys per Seconds
	- Chars per seconds
	- WPM
- Error Rates
	- Error Rate


- Average Word per Minute
- Average Error Rate

## Position

- Mouse
- Trackball
- Joystick
- Touch
- Gestures

### Fitt’s Law

- Which targets are easy to reach and why?

```handdrawn-ink
{
	"versionAtEmbed": "0.3.4",
	"filepath": "Semester 1/Game Engine Concepts/Lectures/attachments/Ink/Drawing/2025.11.11 - 16.33pm.drawing",
	"width": 500,
	"aspectRatio": 1
}
```
- Near and Big Targets are easier to Reach

- Hard Fact in HCI
- Allows to predict pointing tasks
- allies only to liner movements
- training can improve performance

- Original Experiemtnt 1954
- only left right pointing tasks

- Undershooting
- Overshooting


### Implications for Game Interface Designs

- 5 positions
	- Hot Corners
	- current cursor position
- Position, arrangement, and size of context menu items
- growing targets
- pie menus
	- just 1px distance to the target
	- large menus

## Selection

- Choose object from a s et of alternatives
	- cognitively demanding
	- menus, lists
	- no input device
	- selection has impace on performace
	- Hick’s Law models selection task
		- time needed is proportional to log number of alternatives
		- does not apple to linear search → random list
		- alternatives must be related → must be obvious
		- **Consequence** → menus should be ordered


### Selection Performance

- Hick’s Law
	- used to decide how manus are arranged
	- lists should be ordered
	- icons make search easier → but not related to hicks law
	- if large list has sub lists, does not apple

- Simple Tasks
	- Time needed to select a colored button depending on the color of a light inceases with numbrer of options
- Martials arts
	- Time needed to block an attack with the number of blocking techniques
- Trafic signs
- Braking


## Hierarchical Task Analysis

- What is the analysis for
- define goals
- data
- draft hierarchical diagram
- evaluate the decomposition
- identify important operations
- design based on “Selecting / supporting touchpoints in space and time”

### Shooting a ball

- Find / Grab a ball
	- Finding a ball
		- Perceiving ball location
			- Look in environment
			- Look on map
		- Move to ball location
	- Grabbing the Ball
		- Bend Down
		- Pick ball
			- stretch arms
			- move fingers
			- reposition
- Throwing the ball
	- Selecting direction
		- look towards target
		- lunge with arm
		- orient arm
	- Throwing
		- Select Force
		- Throw
			- Move arm forwards
			- open hand

 > What are the users task?