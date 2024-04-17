# Project Proposal


## MVP1
### Data Selection
We have choosen the number of daily cases in the whole UK found [here](https://coronavirus.data.gov.uk/details/cases)
    - separate daily cases by raising and declining trends to be able to apply piece-wise linear regression
    - allows for near-future approximation by extending the last line

    - we need to find a way to separate different lines for piece-wise regressin (OLS for example)
    - prototype in a prototype language (R, Python, etc.)
    - implement final solution in Java
## MVP2
    - go towards applying a polynomial curve
    - allows for a theoretically more accurate near-future and further future prediction 

## MVP3
### Data Selection
We could start looking at separating different areas of the UK, ie. by country, or by counties