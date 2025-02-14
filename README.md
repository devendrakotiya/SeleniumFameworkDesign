my framework e2e flow
1. Landing page
2. Product Catalogue
3. Abstract class - we created this to put reusable code so that we can access this( base page object file)
4. cart page
5. checkout page
6. Base test - stores browser config detail and global objects
as of know we have abstract class as a base page object file but for test also let's have one base file
7. error validation class

points:
1. implemented dependsonMethod
2. implement parallel/grouping test exec
3. introduced global property
4. i used hashmap for data providing and also tried to take data from JSON file using getJsonDataToMap(file_path) function
5. used screenshot utility to take screenshot
6. used extent report concept in my framework
7. i used TestNg Listners in my framework to take screenshot when failure occurs