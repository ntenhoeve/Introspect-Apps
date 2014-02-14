@echo off
echo
echo
echo Dit zal de belangrijkste documenten uit my documents copieren naar een externe NAS drive
echo Controleer of N: drive gelinked is aan de NAS of annuleer met crtl-c
pause
java -jar SynchronizeFolders.jar synchronize "C:\Users\nilsth\Documents" "N:\My Documents" false true
java -jar SynchronizeFolders.jar synchronize "C:\Users\nilsth\My E Books" "N:\My E Books" false true
java -jar SynchronizeFolders.jar synchronize "C:\Users\nilsth\My Favorites" "N:\My Favorites" false true
java -jar SynchronizeFolders.jar synchronize "C:\Users\nilsth\My Galaxi" "N:\My Galaxi" true true
java -jar SynchronizeFolders.jar synchronize "C:\Users\nilsth\My Java" "N:\My Java" true true
java -jar SynchronizeFolders.jar synchronize "C:\Users\nilsth\My Music" "N:\My Music" true true
java -jar SynchronizeFolders.jar synchronize "C:\Users\nilsth\My Outlook Archives" "N:\My Outlook Archives" true true
java -jar SynchronizeFolders.jar synchronize "C:\Users\nilsth\My Pictures" "N:\My Pictures" false true
java -jar SynchronizeFolders.jar synchronize "C:\Users\nilsth\My Videos" "N:\My Videos" false true
