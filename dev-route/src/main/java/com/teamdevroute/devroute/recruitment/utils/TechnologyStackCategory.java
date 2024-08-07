package com.teamdevroute.devroute.recruitment.utils;

import com.teamdevroute.devroute.user.enums.DevelopField;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.teamdevroute.devroute.user.enums.DevelopField.*;

@Component
public class TechnologyStackCategory {

    private Map<DevelopField, Set<String>> technologyStackCategories;

    public TechnologyStackCategory() {
        this.technologyStackCategories = new HashMap<>();
        initializeBackendCategory();
        initializeFrontendCategory();
        initializeAICategory();
        initializeDataScienceCategory();
        initializeMobileCategory();
    }

    private void initializeBackendCategory() {
        technologyStackCategories.put(BACKEND, new HashSet<>(Arrays.asList(
                "Java", "Spring", "AWS", "MySQL", "Git", "SpringBoot", "Python",
                "Node.js", "Linux", "C++", "Docker", "MariaDB", "RestAPI",
                "PostgreSQL", "JPA", "PHP", "C#", "NoSQL", "Redis", "Azure", "MSSQL", "OracleDB",
                "MongoDB", "DevOps", "GCP", "MyBatis", "Kafka", "Jenkins", "SaaS", "Kubernetes",
                ".NET", "Django", "Nginx", "Apache", "Tomcat", "PL/SQL", "HTTP", "Unix", "Eclipse",
                "Bootstrap", "GraphQL", "ASP.NET", "Objective-C", "GoLang", "WPF", "Scala", "Rust"
        )));
    }

    private void initializeFrontendCategory() {
        technologyStackCategories.put(FRONTEND, new HashSet<>(Arrays.asList(
                "React", "Vue.js", "Angular", "JavaScript", "TypeScript", "HTML", "CSS",
                "Sass", "LESS", "Webpack", "Babel", "npm", "Yarn", "Bootstrap", "jQuery",
                "Redux", "GraphQL", "CSS3", "HTML5", "D3.js", "React-Native", "Ajax", "WebGL"
        )));
    }

    private void initializeMobileCategory() {
        technologyStackCategories.put(MOBILE, new HashSet<>(Arrays.asList(
                "Flutter", "Swift", "Kotlin", "React-Native",
                "Objective-C", "Java", "Xamarin", "Cordova", "Ionic", "Unity",
                "Unreal", "C#", "C++", "HTML5", "jQuery Mobile", "PhoneGap",
                "Appcelerator", "Sencha Touch", "BlackBerry", "Windows Mobile"
        )));
    }

    private void initializeAICategory() {
        technologyStackCategories.put(AI, new HashSet<>(Arrays.asList(
                "Python", "C++", "Java", "Pytorch", "Tensorflow", "Keras",
                "OpenCV", "Scikit-learn", "NumPy", "Pandas", "SciPy",
                "Hadoop", "Spark", "R", "Matlab", "AWS", "Azure", "GCP",
                "Docker", "Kubernetes", "Flask", "Django", "FastAPI"
        )));
    }

    private void initializeDataScienceCategory() {
        technologyStackCategories.put(DATA_SCIENCE, new HashSet<>(Arrays.asList(
                "BeautifulSoup", "Scrapy", "Requests", "Puppeteer", "Axios",
                "psycopg2", "PyMySQL", "MySQL", "PostgreSQL", "Oracle", "SQLite",
                "MongoDB", "Cassandra", "Redis", "Apache Hadoop", "Apache Spark",
                "Google BigQuery", "Pandas", "Dplyr", "Python", "R",
                "SAS", "SPSS", "STATA", "Matplotlib", "Seaborn", "Plotly", "ggplot2",
                "Shiny", "Tableau", "Power BI", "Looker", "Scikit-learn", "TensorFlow",
                "Keras", "PyTorch", "caret", "mlr", "H2O.ai", "Google Cloud AutoML"
        )));
    }

    public Set<String> getStackCategoryByType(DevelopField type) {
        return technologyStackCategories.get(type);
    }
}
