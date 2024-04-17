# Story structure
- Type: Epic
- Summary: Build COVID-19 information system.
- Description: To do this, the NHS wants you to build a computer system that reads the publicly available UK COVID-19 data from CSV files. The the system should convert the raw data into a report that can be read by the NHS management and easily interpreted.
- Story Points:
    - Type: Epic
    - Summary: COVID-19 Information System MVP
    - Description: Fulfill the MVP requirements for week 5
    - Story Points: 

        - Type: Story
        - Summary: Choose & analyse data
        - Description: Out of the available sources we should choose the data we would like to use, do some sort of analysis and determine the usecase, how is it going to help us and what methods are we going to use to prepare the data for interpretation.
        - Story Points:

        - Type: Story
        - Summary: Import of COVID-19 data.
        - Description: Read the publicly available UK COVID-19 data from CSV files. Automatically pull in and store data from the following government webpages:  https://coronavirus.data.gov.uk/cases and https://coronavirus.data.gov.uk/deaths
        - Story Points:

        - Type: Story
        - Summary: Interpret COVID-19 data.
        - Description: 
            - Convert the raw data into a report that can be read by the NHS management and easily interpreted.
            - The reports should include some statistics to describe the weekly development of COVID-19 infections and death
            - There should be some visualisations of the COVID-19 data. The visualisation should also include modeling and forecasting results.
            - The report should summarize the expected (forecasted) number of infections and deaths in the next 2, 4 and 6 week time frames.
            - The final reports should be presented in a suitable format for the NHS to be able to read easily, in PDF format and/or displayed on the screen.
            - The NHS suggests you fit the data using piecewise linear functions and use linear regression to forecast the data.
                - The team needs to make a decision on what algorithm to use.This depends on how accurate the approximation must be. The more lines are used, the more similar the curves, i.e. the smaller the difference between the curve and the approximation. However, using many lines may increase the  computational complexity of the algorithm.
        - Story Points:

        - Type: Story
        - Summary: Implementing linear regression algorithm
        - Description:
            - We need to implement the algorithm that we decided to choose in the code base.
            - We need to do research on how the algorithm works. You will find more details about the method in maths, statistics and data science textbooks, scientific papers and of course on the internet. You can also discuss this with your team supervisor during the team meetings.
            - Interpret or create the principle into our programming language of choice
            - Test, QA and integrate into our system.
        - Story Points:
    - Type: Epic
    - Summary: Extra features to aid the central objective.
    - Description: The clients would welcome any useful extras that your team can think of in developing the system, provided they are feasible to be implemented to a high quality within the project's timescale. They will especially appreciate any features that aid them in their central objective, i.e. making informed analysis and forecast of COVID-19 development, but other features aiding a smoothly operating system, including good GUI interface design, will be appreciated too.
    - Story Points:










