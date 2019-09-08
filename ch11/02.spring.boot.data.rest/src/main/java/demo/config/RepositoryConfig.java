package demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import demo.entities.Comment;
import demo.entities.Post;
import demo.entities.User;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        
    	config.exposeIdsFor(User.class, Post.class, Comment.class);

        config.getCorsRegistry()
        		.addMapping("/**")
	                //.allowedOrigins("http://localhost:3000")
	        		.allowedOrigins("*")
	                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "DELETE")
	                .allowCredentials(false)
	                .maxAge(3600);
    }
}

