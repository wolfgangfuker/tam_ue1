# tam_ue1

WarehouseScriptDemo ->

!***< hidden

!define TEST_SYSTEM {slim}
!path D:\Privat\tam_git\tam_ue1\tam_uebung1\target\classes
!path D:\Privat\Installs\selenium-server-standalone-3.0.0.jar
*!
Lets check our article type loading

|wolfgangfuker.technikum.tam.WarehouseDecisionFixture	|
|article type 	|loaded?								|
|MAGA 			|true	 								|
|BOOK			|true									|
|FILM			|false									|



WarehouseDecisionScript ->

!***< hidden

!define TEST_SYSTEM {slim}

!path D:\Privat\tam_git\tam_ue1\tam_uebung1\target\classes
!path D:\Privat\Installs\selenium-server-standalone-3.0.0.jar

*!

Lets check our ordering workflow

|script 						|wolfgangfuker.technikum.tam.WarehouseScriptFixture		|
|load article types 																	|
|query articles with user code 	| 009997 												|
|select article type			| MAGA													|
|buy article 					| Newsweek 		|with amount 	| 2 					|
|check 							|result status 	|Orders fulfilled = 1 Response #:1001 	|
