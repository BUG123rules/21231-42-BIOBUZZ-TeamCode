<div align="center">

# Team 21231 · 42 — Official TeamCode

### FIRST® Tech Challenge · 2026–2027 Season · *BioBuzz*

![FTC Team](https://img.shields.io/badge/FTC_Team-21231-f57e25?style=for-the-badge)
![Season](https://img.shields.io/badge/Season-2026--2027-4a90d9?style=for-the-badge)
![Game](https://img.shields.io/badge/Game-BioBuzz-f5c518?style=for-the-badge)
![Language](https://img.shields.io/badge/Java-FTC_SDK-5382a1?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## About

Welcome to the official code repository for **FTC Team 21231, 42**! This repo holds all of our robot code for the **BioBuzz** season TeleOp, auto, subsystems, and the tuning tools are all located in this package.

We believe FTC is at its best when all teams can learn from each other and share what they know. That's why this repo is **public and regularly updated**. Whether you're a rookie team looking for a starting point or a veteran team curious how we approached a problem, you are welcome to read, learn from, use, and build on **any** code in this repo.

## Our Open-Source Commitment

> **Every change to our robot code is committed to this repo within 7 days of being made.**

We are resolved to keep this repo as up to date as we possibly can. We want teams to be able to see our robot and immediately see a life, accurate reflection of what is our most current code. Our hope in this is to allow teams to use our code as soon as possible while it is still relavant and *useful to other teams*

## Libraries We Use

Our code is built on top of several amazing repositories created by some true genius's. The people upkeeping these repos are all other members of the FTC community in some way and we would not be able to do what we do without them. Huge thanks to all the teams listed below

| Library | What we use it for | Created by |
|:--|:--|:--|
| **[Sloth](https://github.com/Dairy-Foundation/Sloth)** | Lightning fast code hotloading, Allows much faster testing and updating code while at comp or just during practice. Allows TeamCode loads to go from 40+ seconds down to < 1s | **[Dairy Foundation](https://github.com/Dairy-Foundation)** |
| **[Pedro Pathing](https://pedropathing.com)** | The single **best** pathing tool out there for FTC. Allows us to have stupid fast autos, with automatic tuning, and a simple visualizer. Changes the game | **FTC Team 10158, Scott's Bots** |
| **[SolversLib](https://github.com/FTC-23511/SolversLib)** | First used last year for coordinating our spindexer, proved to be invaluable in timing our autos and shot cycles. Used this year for our shot cycles again for ease of programming | **FTC Team 23511, Seattle Solvers** |
| **[Panels](https://github.com/ftcontrol/ftcontrol-panels)** | Real time dashboard for tuning PIDs, starting and stopping auto OpModes without a driver hub, And monitoring telemetry. Also used for graphing robot position to ensure minimal odometry drift | **Lazar of FTC Team 19234, ByteForce** |

Additional thanks to:
- **FIRST** and the **FTC Technology Team** for the [FTC SDK](https://github.com/FIRST-Tech-Challenge/FtcRobotController) this project is built on.
- The original **[FTCLib](https://github.com/FTCLib/FTCLib)** contributors, whose work is present in solverslib.
- **Oscar** and the Dairy Foundation for hosting the Maven repository that several of these libraries are distributed through.

If you find these libraries useful, please go star their repos and check out their documentation

## Getting Started

1. **Clone** this repository:
   ```bash
   git clone github.com/BUG123rules/21231-42-BIOBUZZ-TeamCode
   ```
2. **Open** the project in [Android Studio](https://developer.android.com/studio).
3. **Sync Gradle** — all library dependencies are already configured in the build files.
4. **Connect** to your Robot Controller and deploy.

> 💡 Because we use Sloth, a majority of changes inside the `teamcode` package can be hot loaded without a full install. Changes to Gradle files or new dependencies still require a normal build and install. Please also ensure that you have sloth installed on your robot and your gradle tasks set up. Without this the OpModes <u>will</u> break

As you look at our code you will find three main folders: Main, Asher, and Rio. Please not that *Main* will always be the best place to find code. The other folders are dedicated as a workspace for rookies and are not part of the competition build unless moved to main


## Using Our Code

All teams are more than welcome to reference, adapt, or even flat out borrow anything found in this repo. Our code is public to all no matter what and we will always work to keep it that way. We only ask that when you use from our repo you:

- **Give credit** to our team, Team 21231 42 if you use any significant portion of our code.
- **Credit the library authors** above if you use their work too, they worked hard to make their libraries and deserve credit.
- **Understand what you use.** The best way for our code to legitimately help you learn more about FTC and programming for FTC is to read it, and understand it, not just copy and paste. 

If you ever have any questions about how some part of our code works please open an issue, we want to help and will respond as soon as we can. We encourage you to ask us questions.

---

<div align="center">

**Made by FTC Team 21231 · 42**

*Gracious Professionalism® in action. Built by the community, shared with the community.* Always to encourage learning and inspire students to pursue STEM

</div>
