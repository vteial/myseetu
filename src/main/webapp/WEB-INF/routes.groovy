email						to : '/receiveEmail.groovy'

get 	'/favicon.ico',		redirect : '/images/favicon.png'
get     '/',				redirect : '/index'
get     '/index',			forward  : '/index.groovy'
get 	'/info',			forward  : '/info.groovy'
get 	'/build-info',		forward  : '/build-info.groovy'
get		'/ping',			forward  : '/ping.groovy'
get		'/forbidden',		forward  : '/forbidden.groovy'
all 	'/_ah/warmup',		forward  : '/ping.groovy'

get  	'/sessions/properties',	                        forward : '/io/wybis/seetu/web/session/properties.groovy'
post 	'/sessions/sign-up',     	                    forward : '/io/wybis/seetu/web/session/signUp.groovy'
get 	'/sessions/sign-up-confirm',                    forward : '/io/wybis/seetu/web/session/signUpConfirm.groovy'

post 	'/sessions/sign-in',     	                    forward : '/io/wybis/seetu/web/session/signIn.groovy'
get  	'/sessions/sign-out',    	                    forward : '/io/wybis/seetu/web/session/signOut.groovy'
post 	'/sessions/change-details',     	            forward : '/io/wybis/seetu/web/session/changeDetails.groovy'
post 	'/sessions/change-password',     	            forward : '/io/wybis/seetu/web/session/changePassword.groovy'
