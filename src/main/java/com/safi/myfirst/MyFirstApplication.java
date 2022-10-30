package com.safi.myfirst;

import com.safi.myfirst.dao.TodoRepository;
import com.safi.myfirst.domaino.User;
import com.safi.myfirst.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
public class MyFirstApplication implements WebMvcConfigurer
{

	public static void main(String[] args) {

		ApplicationContext c = SpringApplication.run(MyFirstApplication.class, args);

		for(String name : c.getBeanDefinitionNames()){
			System.out.println(name);
		}
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return  new BCryptPasswordEncoder();
	}



	@Bean
	CommandLineRunner run(UserService userService){
		return  args -> {


			userService.create(new User("safi","123123", new String[]{"ADMIN"}));


		};
	}





	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
	}

	class LoggingInterceptor implements AsyncHandlerInterceptor
	{
		private static final Log LOG = LogFactory.getLog(LoggingInterceptor.class);


		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
		{
			LOG.info(request.getMethod());
		}
	}
}
