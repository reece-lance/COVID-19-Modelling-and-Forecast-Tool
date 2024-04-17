# Idea Proposal #
## Feature 1 ##
### User chooses PDF output content ###
When exporting the data as a pdf, the user should be able to choose what to include:
- Graphs
- Metrics
- Font
  - Font size
  - Font type
  - Font colour

Having these options would allow the application to generate a pdf better suited to the user's needs or requirements. Not only will this allow the pdf to only have the chosen data and not any extra unnecessary data or metrics, but allowing the user to choose their own font style will make the output more user friendly for users with disabilities, such as the visually-impaired.

This could be implemented into our application in the export tab with combo boxes allowing the user to choose a font size, type and colour; the metrics could be selected using check boxes. We have already used combo boxes from JSwing in our program and check boxes can also be implemented the same way.

The adaption of the pdf output would be simple, once we fix the html hardcoded table, by using Moustache. The font style can be set when the export button is pressed and added to the HTML through Moustache. The metrics can be added to a java dictionary with key being the metric name and value being the metric value. Theamount of rows in a table can be adjusted and data implemented via Moustache also.

## Feature 2 ##
### PDF preview ###
Before exportation as a PDF, the user should be able to view the way the PDF looks.

This preview could be added in the form of a browser in a JPanel within the export tab page, like the browser to show the graph. Only this browser would show the generated XHTML code.

This browser could be updated in 2 ways:

1. Upon each combo and check box clicked (If feature 1 is implemented)

OR

2. When a separate "View PDF Output" button is pressed

In my opinion, option 2 would be most suitable as it would remove any unneccessary updates to the browser. There are also no impactful negatives to doing option 2 compared to option 1.

