# Product Context

## Legal

### Third-party data
- [disease.sh](https://disease.sh/)
    - Used to fetch Worldometer data for country comparison
    - We cannot charge users any fee for the use of our application unless we come to a separate agreement with NovelCOVID  or obtain NovelCOVID's written permission
    - We are obliged to remove content from our application "_provided through the NovelCOVID API that is alleged to infringe the rights of third parties to the extent required by applicable law or upon request of removals._" [source](https://github.com/disease-sh/API/blob/master/TERMS.md)
- [UK COVID-19 dashboard API](https://coronavirus.data.gov.uk/)
    - Used to fetch data regarding the UK and it's regions/lower-tier local authority/upper-tier local authority areas
    - Used to fetch data regarding COVID-19 cases/deaths and additional measures
    - OGLv3.0 - Open Government License [source](https://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/)
    - We are allowed to
        - "_copy, publish, distribute and transmit the Information;_"
        - "_adapt the Information;_"
        - "_exploit the Information commercially and non-commercially for example, by combining it with other Information, or by including it in your own product or application._"
- [Find that Postcode](https://findthatpostcode.uk/)
    - Used to fetch GeoJSON files for given area code for projection and rasterization
    - Postcode data from ONS used under Open Government License [source](https://www.ons.gov.uk/methodology/geography/licences)
    - "_Contains OS data © Crown copyright and database right 2021_"
    - "_Contains Royal Mail data © Royal Mail copyright and database right 2021_"
    - "_Contains National Statistics data © Crown copyright and database right 2021_"

### Licensing our software
This is a very opinionated and key strategic point. Because we are currently working our way through the academia experience, where gaining and sharing knowledge is one of the main pursuits, we might want to choose to make the source code available under an open-source license. For example:
- MIT
    - Very lean
    -  "_Do whatever you want with my stuff, just don't sue me_[source](https://gist.github.com/nicolasdao/a7adda51f2f185e8d2700e1573d8a633) 
- BSD 3-clause
    - Also very lean
    - Protects our name/brand by requiring permission before mentioning/citing in a derived piece of software [source](https://opensource.org/licenses/BSD-3-Clause)

Considering we are currently required to provide our product for free because we have not attempted to come to any exempting condition with any of our data providers, we might want to provide a more restrictive license or no license at all and only leverage the copyright law "_All rights reserved_..." as of not to hinder off potential stakeholders in case we opted to make those arrangements. Example of a more restrictive license:
- BSD 4-clause 
    - Additionally to what BSD 3-clause states, all advertising materials mentioning features or uses of software must display a predefined statement specifying the authoring body [source](https://spdx.org/licenses/BSD-4-Clause.html)

Finally, considering the real-life events many of which have inspired this product to make the information regarding COVID-19 more available and interpretable in order to increase informativeness, to help the good cause and more importantly to help the public effort, we have decided that if we were to publish this software we would do so under the BSD 3-clause license.

### Data protection
The most relevant data protection law to consider is GDPR and it's UK's derivative. Since our application is not collecting, storing or sending any personal information, nor do our sources as they provide their data already anonymized, we are not considered to be the processors of personal information and are thus not obliged to take any safety measures or make any disclaimers about the usage of such.

## Ethical
We are not responsible for the daa source and in no event shall we be held accountable for any direct, indirect, incidental, special, exemplary, or consequential damages arising in any way out of the use of this software.

## Health and Safety
The information provided by the product is close to accurate, we do not take responsibility for harm cuased due the informaition provided by our product.