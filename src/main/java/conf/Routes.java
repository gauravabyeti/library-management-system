/**
 * Copyright (C) 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;

import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;

//import controllers.ApiController;
import controllers.ApplicationController;
//import controllers.ArticleController;
//import controllers.LoginLogoutController;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) {  
        
        // puts test data into db:
       
        
        ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
//       router.GET().route("/login").with(LoginLogoutController::login);
//        router.POST().route("/login").with(LoginLogoutController::loginPost);
//        router.GET().route("/logout").with(LoginLogoutController::logout);
//        
        ///////////////////////////////////////////////////////////////////////
        // Create new article
        ///////////////////////////////////////////////////////////////////////

        
        ///////////////////////////////////////////////////////////////////////
        // Create new article
        ///////////////////////////////////////////////////////////////////////
       // router.GET().route("/article/{id}").with(ArticleController::articleShow);

        ///////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////   
    	router.GET().route("/").with(ApplicationController::index);
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        router.POST().route("/bookdetails/{title}/{category}/{author}").with(ApplicationController.class, "postBookDetails");
        //router.POST().route("/bookdetails").with(ApplicationController.class, "postBookDetails");
        router.GET().route("/bookdetails").with(ApplicationController.class, "getBookDetails");
        router.POST().route("/admindetail").with(ApplicationController.class, "postAdminDetail");
        router.POST().route("/admin").with(ApplicationController.class, "postAdmin");
        router.GET().route("/admindetail").with(ApplicationController.class, "getAdminDetail");
        router.POST().route("/bookissued/{bookid}/{issuedate}/{returndate}/{studid}").with(ApplicationController.class, "postBookIssued");
        router.GET().route("/bookissued").with(ApplicationController.class, "getBookIssued");
        router.GET().route("/bookissuedbystudent/{id}").with(ApplicationController.class, "getBookIssuedByStudent");
        router.POST().route("/student").with(ApplicationController.class, "postStudent");
       // router.GET().route("/student").with(ApplicationController.class, "getStudent");
        router.POST().route("/students").with(ApplicationController.class, "postStudents");
        router.POST().route("/suggestion/{comment}").with(ApplicationController.class, "postSuggestion");
        router.GET().route("/suggestion").with(ApplicationController.class, "getSuggestion");
        //router.POST().route("/bookavailable").with(ApplicationController.class, "postBookAvailable");
        router.POST().route("/bookavailable/{id}/{number}").with(ApplicationController.class, "postBookAvailable");
        router.GET().route("/bookavailable").with(ApplicationController.class, "getBookAvailable");
        router.GET().route("/hello_world.json").with(ApplicationController::helloWorldJson);
        router.GET().route("/studentpanel").with(ApplicationController::studentPanel);
        router.GET().route("/adminpanel").with(ApplicationController::adminPanel);
        router.GET().route("/adminlogin").with(ApplicationController::adminLogin);
        router.GET().route("/studentlogin").with(ApplicationController::studentLogin);
        router.GET().route("/newregistrationadmin").with(ApplicationController::adminRegistration);
        router.GET().route("/newregistrationstudent").with(ApplicationController::studentRegistration);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        //router.GET().route("/.*").with(ApplicationController::index);
    }

}
