# PM:AM

#### By **Illia Kuchko, Kassidy Douglas, and Nathan Romike**

## Description

> PM:AM  is a project management web app written in Java and implemented in Spark. The intent of this project is to break up larger projects into smaller parts (Epics, Stories, and Tasks respectively), and allow different permissions for project managers and developers. Such functionality includes assigning and completing tasks, viewing the number of tasks and stories on a particular project-- as well as how many developers are assigned to these tasks-- and seeing the efficiency of developers by tracking time completion on assigned tasks.

## Setup

* Clone repository to local machine.
* Start the database system with terminal command `$ postgres`, leave this window open and runnining.
* In a new terminal window run `$ psql`
* Within psql enter `=# CREATE DATABASE pm_am;`
* In a new terminal within the pm_am directory enter `$ psql pm_am < pm_am.sql`
* Return to the terminal running psql and enter `=# CREATE DATABASE shoe_store_test WITH TEMPLATE pm_am;`
* Compile the web application in terminal by entering `$ gradle run`, and navigate to [localhost](http://localhost:4567/).

### Or Follow this link:

```
http://thawing-brook-29610.herokuapp.com/
```

## Legal

Copyright (c) 2016 **_Illia Kuchko, Kassidy Douglas, and Nathan Romike_**

This software is licensed under the MIT license.

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
