---
layout: page
title: Rohit's Project Portfolio Page
---

### Project: NUS ModBook

NUS ModBook is a desktop application for NUS students to manage modules, optimized for use via a Command Line Interface.
I was the Documentation Lead for this project.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=rohit&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=rohit0718&tabRepo=AY2122S1-CS2103T-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **New Features**:
  * Added the representation for Modules in the ModBook [#55](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/55)
    * Created Module, ModuleName, and ModuleCode classes, along with all fields and methods for them.
    * Added methods in ParserUtil to parse Modules, ModuleNames and ModuleCodes 
    * Added tests for Module, ModuleName, and ModuleCode and new ParserUtil methods

  * Added ability to delete Modules, Exams, Lessons [#82](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/82)
    * Added `delete` commands (for Modules, Exams, and Lessons) and corresponding parsers
    * Added tests for all classes and methods added
    * Added deepCopy method for Module allowing for tests to act on Modules in an immutable manner

* **Enhancements to existing features**:
  * Updated ModBook GUI to show Modules, Lessons, and Exams with different views based on command that user types in [#61](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/61), [#68](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/68)
    * Changed GUI to read data from data/modbook.json
    * Update GUI with ModBook references (Modules, Exams, Lessons)
    * Added various GUI cards to showcase Modules, Exams, and Lessons (ModuleDetailCard, ModuleExamsCard, ModuleLessonsCard respectively)
    * Added logic to change GUI view depending on command that was executed
  * Added minor improvements to GUI to improve User Experience
    * Added numbering for Module list to allow users to execute index based commands (delete and edit) easily [#87](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/87)
    * Presented a message to users when the ModBook is empty (no Modules added yet) [#105](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/105)

* **Documentation**:
  * User Guide:
    * Added information about command execution order restrictions [#108](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/108)
    * Added information about invalid indexes [#130](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/130), [#133](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/133)
    * Fixed bugs in the UG found in the Mock PE [#182](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/182)
    * Added screenshots of ModBook
    * Added section on screen views
    * Added `delete` command details
    * Added valid Day formats table
  * Developer Guide:
    * Added information of GUI implementation, with updated UML diagrams [#112](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/112)

* **Contributions to team-based tasks**:
  * Helped to close and triage bugs found from Mock PE
  * Maintained collaborative HackMD document for UG and DG and ensured that all changes were synced with the project UG and DG files
  * Added helper functions to ease implementation of commands [#74](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/74)
  * Fixed various bugs: [#77](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/77), [#130](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/130)

* **Reviewing/Mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [#69](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/69), [#70](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/70), [#96](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/96), [#94](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/94), [#101](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/101), [#113](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/113), [#104](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/104), [#121](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/121), [#186](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/186), [#188](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/188)

* **Contributions beyond the project team**:
  * Helped out in the forum ([link](https://github.com/nus-cs2103-AY2122S1/forum/issues?q=is%3Aissue+is%3Aclosed+commenter%3Arohit0718+))
  * Reported bugs for other teams as part of Mock PE ([link](https://github.com/rohit0718/ped/issues))
