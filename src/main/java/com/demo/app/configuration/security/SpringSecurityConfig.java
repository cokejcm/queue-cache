package com.demo.app.configuration.security;

//@Configuration
//@EnableWebSecurity
//@Order(2)
public class SpringSecurityConfig {// extends WebSecurityConfigurerAdapter {

	// private final UserService userService;
	// private final TokenAuthenticationService tokenAuthenticationService;

	/*
	 * public SpringSecurityConfig() { super(true); this.userService = new
	 * UserService(); tokenAuthenticationService = new
	 * TokenAuthenticationService("tooManySecrets", userService); }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http .exceptionHandling().and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
	 * STATELESS).and() // .headers().cacheControl().and().and()
	 * .authorizeRequests() .antMatchers("/login").permitAll() // .antMatchers(
	 * // HttpMethod.GET, // "/", // "/*.html", // "/favicon.ico",
	 * //.antMatchers("/**").authenticated(); // All other request need to be
	 * authenticated // .anyRequest().authenticated(); // .and();
	 * 
	 * // http // Custom Token based authentication based on the header //
	 * previously given to the client // .addFilterBefore(new //
	 * StatelessAuthenticationFilter(tokenAuthenticationService), //
	 * UsernamePasswordAuthenticationFilter.class); /*}
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth)
	 * throws Exception { auth.userDetailsService(userDetailsService())
	 * .passwordEncoder(new BCryptPasswordEncoder()); }
	 * 
	 * @Bean
	 * 
	 * @Override public AuthenticationManager authenticationManagerBean() throws
	 * Exception { return super.authenticationManagerBean(); }
	 * 
	 * @Bean
	 * 
	 * @Override public UserService userDetailsService() { return userService; }
	 * 
	 * @Bean public TokenAuthenticationService tokenAuthenticationService() {
	 * return tokenAuthenticationService; }
	 */

}
