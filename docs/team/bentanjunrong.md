---
layout: page
title: Benjamin Tan's Project Portfolio Page
---

### Project: NUS ModBook

NUS ModBook is a desktop application for NUS students to manage modules, optimized for use via a Command Line Interface.
For this project, I was the Code Quality Lead.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=bentanjunrong&tabRepo=AY2122S1-CS2103T-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **New Features**:
    * Added the representation for `Lessons` in the ModBook [#53](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/53)
        * Created `Lesson`, `LessonName` and `Day` classes, along with all fields and methods for them
        * Added methods in ParserUtil to parse Lessons, LessonNames and Days
        * Added tests for Lesson, LessonName, and Day and their respective ParserUtil methods
    * Added functionality to **edit** Modules, Exams, Lessons [#101](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/101)
        * Added `edit` commands (for Modules, Exams, and Lessons) and corresponding command parsers
        * Added `EditModDescriptor`, `EditExamDescriptor` and `EditLessonDescriptor` classes to store the details of each edit command
        * Added tests for all relevant classes and methods added
        * Added utility methods to recreate command strings based on the `EditModDescriptor`, `EditExamDescriptor` and `EditLessonDescriptor`

* **Enhancements to existing features**:
    * Added flexible constructors and readable string for the Day enum [#131](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/131)
  * Adapt `Model` interface and `ModelManager` class [#60](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/60)
      * Added relevant CRUD methods for modules in the `ModelManager` and `ModBook` classes
      * Implemented `UniqueModuleList` class to handle list operations for modules
      
* **Documentation**:
    * User Guide:
        * Added `edit` command details
        * Added information about timeslot constraints for edit commands
        * Added multiple examples for commands with optional inputs (`edit`)
        * Added relevant screenshots [#126](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/126)
    * Developer Guide:
      * Contributed to `Model.java` section with relevant information and diagram [#114](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/114), [#188](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/188)
      * Updated Architecture diagram [#188](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/188)
      * Standardised formatting in preparation for final submission
      * Updated command guide for manual testing
      * Updated project links to relevant files for each component
    * Index Page/ ReadMe Page
      * Added information, application screenshot and relevant links to other documents

* **Contributions to team-based tasks**:
    * Helped to close and triage bugs found from Mock PE: [#179](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/179)
    * Contributed to and formatted various portions of the User Guide, Developer Guide, and Index page
    * Reviewed code for code quality and adherence to OOP principles
    * Fixed various bugs: [#56](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/56), [#89](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/89), [#93](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/93), [#142](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/142), [#143](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/143), [#153](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/153), [#164](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/164), [#184](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/184), [#187](https://github.com/AY2122S1-CS2103T-T13-1/tp/issues/187),

* **Reviewing/Mentoring contributions**:
    * PRs reviewed (with non-trivial review comments): [#55](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/55), [#103](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/103), [#106](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/106), [#122](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/122), [#127](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/127), [#175](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/175), [#177](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/177), [#100](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/100)

* **Contributions beyond the project team**:
    * [Reported bugs](https://github.com/bentanjunrong/ped/issues) for other teams as part of Mock PE 
