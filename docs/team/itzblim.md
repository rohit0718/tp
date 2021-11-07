---
layout: page
title: Benjamin Lim's Project Portfolio Page
---

### Project: NUS ModBook

NUS ModBook is a desktop application for NUS students to manage modules, optimized for use via a Command Line Interface.
I was in charge of the deliverables and deadlines for this project.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=itzblim&tabRepo=AY2122S1-CS2103T-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **New Features**:
    * Added the representation for Exams in ModBook [\#42](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/42)
        * Created the class to encapsulate Exams and its methods
        * Added representations for Venues and Dates
        * Added methods to parse exams, venues and dates
        * Added tests for the corresponding classes
    * Added the representation for ModBooks [\#60](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/60)
        * Created the class to encapsulate ModBooks
        * Modified the `Model` to manage ModBooks
    * Added ability to list modules, exams and lessons [\#79](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/79)
        * Added the `list mod`, `list lesson` and `list exam` commands and their corresponding parsers
        * Added tests for each new command

* **Enhancements to existing features**:
    * Updated Storage to be able to store Modules and ModBooks in JSON formats [\#60](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/60)
        * Adapted these classes for JSON
        * Updated `Storage` to store ModBook data
        * Created the `ModBookBuilder` and `TypicalModules` classes
        * Added tests for the Storage component
    * Removed AB3 dependencies [\#104](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/104)
        * Removed all obsolete AB3 classes and methods from various components of the project

* **Documentation**:
    * User Guide:
        * Wrote command descriptions for modules, lessons and exams
        * Added hyperlinks for the table of contents and command summaries
    * Developer Guide:
        * Added implementation details and design considerations for the `Storage` component [\#110](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/110)

* **Contributions to team-based tasks**:
    * Managed and uploaded releases
    * Managed sample data for initial runs of ModBook [\#125](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/125)
    * Renamed archive [\#115](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/115)
    * Fixed various bugs: [\#94](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/94), [\#84](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/84), [\#132](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/132), [\#178](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/178)

* **Reviewing/Mentoring contributions**:
    * PRs reviewed: [\#57](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/57), [\#78](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/78), [\#80](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/80), [\#108](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/108)
    * Reported various bugs: [\#93](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/93), [\#107](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/107), [\#123](https://github.com/AY2122S1-CS2103T-T13-1/tp/pull/123)

* **Contributions beyond the project team**:
    * Reported bugs for other teams as part of Mock PE ([examples](https://github.com/itzblim/ped/issues))
