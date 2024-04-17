# Showing R number in the UI

Although R number does not describe it all, it gives us a good overview of how much the desease is spreading.

    1) I could try to study the process and make my own estimations
    2) I could fetch that data from the gov.uk site

This property would be viewable in the browser as well as exportable to the pdf.


# Filter data based on location

Show graphs based on location - let the user pick from individual areas. 

The UK COVID API supports filtering by location. I would fetch all the possible locations and cache them in a file, that could be refreshed if requested by the user.
There would be an aditionall select box, where the default option would be all of the uk. Additional options would list the fetched areas or locations.