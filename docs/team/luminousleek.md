---
layout: page
title: Isaac Lee's Project Portfolio Page
---

### Project: NUS ModBook

NUS ModBook is a desktop application for NUS students to manage modules, optimized for use via a Command Line Interface.
I was the team leader for this project.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=luminousleek&tabRepo=AY2122S1-CS2103T-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **New Features**: 
  * Added the representation for Timeslots in ModBook [\#39](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/39)
      * Created the class to encapsulate Timeslots, as well as its various fields and methods
      * Added methods to parse times and timeslots
      * Added support for parsing multiple time formats [\#96](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/96)
  * Added ability to restrict certain commands using the `GuiState` [\#70](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/70)
    * Added `GuiState` as a parameter for parsing and executing commands
    * Modified test utility methods to take `GuiState` into account
    * Made `GuiState` handling more natural and intuitive [\#120](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/120)
    * Update command syntax to make reflect `GuiState` and make it more intuitive [\#122](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/122)
  * Added ability to see the details of a module [\#69](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/69)
    * Added the `detail` command and its corresponding parser
    * Added tests for `detail` command

* **Enhancements to existing features**:
    * Updated Storage to be able to store Lessons, Exams and Timeslots in JSON formats [\#57](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/57)
      * Adapted these classes for JSON
      * Integrated the jackson-datatype-jdk8 package to the project to properly handle `Optionals`
        * This included going into the serialiser to make it print relative rather than absolute paths
      * Wrote tests, and also wrote some utility test methods
    * Update `clear` command to work properly [\#75](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/75)
    
* **Documentation**:
    * User Guide:
        * Wrote command summary table
        * Wrote section on valid time formats [\#96](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/96)
    * Developer Guide:
        * Added implementation details of `GuiState` handling [\#97](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/97)

* **Contributions to team-based tasks**:
  * Set up GitHub team organisation and repo
  * Maintained the issue tracker
  * Enabled java assertions [\#116](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/116)
  * Updated user guide link [\#73](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/73)
  * Fixed various bugs: [\#63](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/63), [\#64](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/64), [\#162](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/162), [\#175](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/175), [\#181](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/181)

* **Reviewing/Mentoring contributions**:
    * PRs reviewed (with non-trivial review comments): [\#60](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/60), [\#82](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/82), [\#101](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/101), [\#103](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/103)
    * Assisted with writing the comparator for the `Day` enum