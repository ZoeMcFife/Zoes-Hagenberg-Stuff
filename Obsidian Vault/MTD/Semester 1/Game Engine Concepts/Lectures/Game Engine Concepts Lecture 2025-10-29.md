#game_engine_concepts #game_loops #physics

# Game Loop

- the game loop is the central component of a game engine
- What is the Issue?
	- how to stop the loop?
	- loop???

```python
function main();
	loop forever:
		# do something
```

- loops run as fast as the comptuer can
- TIME 
- Which **speeds, rates, frequencies etc. are important?**

---
## Timelines

- Real-Time
	- measured by cpu timer
	- not always 100% accurate
- Game Time
	- independent of real time
- Global vs Local time
	- process in the engine have their own local timelines *(audio, animation, etc)*
	- those are mapped to the global time

 - we do not speak of times, but of **rates or frequencies**
 - **frames per second** 
 - physics update rate: ~ 120 per second
 - AI behaviors: ~ 1 per second (depends)

- early games did not consider time in their update loop
- typical way: **measure delta time** since last frame update in game loop
- more sophisticated: **running average** or **fixed update rate**

## Implementation

``` python
initialize last_time <- current_system_time_in_milliseconds()
initialize frame_counter <- 0

loop forever:
	current_time <- current_system_time_in_milliseconds()
	delta_time_ms <- current_time - last_time
	delta_time_s <- delta_time_ms / 1000.0
	
	if delta_time_s >= (1 / framerate):
		delta_time_s <- 1 / framerate
		
		# Game loop setps
		
		CheckUserInput()
		ApplyGamePhysics()
		Render()
		
		last_time <- current_time
		frame_counter <- frame_counter + 1
```

## Physics

- **an object in free fall**
	- y(t) = altitude at tie t
	- t = time
	- v0 = initial velocity
	- y0 = initial height
	- g = gravity

$$
y(t + 1) = v_0 t + y_0 - \tfrac{1}{2} g t^2
$$

- Two **major components**
	- **Collision detection** system
	- **rigid body dynamics** simulation
		- **Rigid body:** idealized, infinitely hard solid object (does not exist in reality)
		- calculates how rigid bodies move and interact with physical forces
	
- Combination of **collisions** and **physics**
	- bouncing between objects
	- sliding under friction
	- rolling until stop

- Do we even need physics?
- Physics can result in chaos
- physics does not per se guarantee fun

