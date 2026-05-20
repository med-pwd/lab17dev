#ReceiverDemo – Lab BroadcastReceiver Android
analyste:souaid med amine
##Description

ReceiverDemo est une application Android simple qui permet de comprendre le fonctionnement des **BroadcastReceiver** en utilisant des événements système et des broadcasts personnalisés.

L’application contient :

* Un **Receiver dynamique** pour le mode avion
* Un **Receiver statique** pour le démarrage du téléphone (BOOT_COMPLETED)
* Un **Broadcast personnalisé** envoyé et reçu  l’application


##Objectifs

Ce projet permet de :

* Comprendre le fonctionnement des BroadcastReceiver
* Faire la différence entre **Receiver statique** et **dynamique**
* Écouter des événements système (Mode avion, démarrage)
* Envoyer et recevoir des broadcasts personnalisés
* Comprendre les restrictions Android modernes (permissions, exported, background)



##Structure du projet


com.example.receiverdemo

MainActivity.java
AirplaneModeReceiver.java
BootReceiver.java
CustomEventReceiver.java

res/layout/activity_main.xml
AndroidManifest.xml




##Fonctionnalités

###Receiver Dynamique (Mode Avion)

* Enregistré dans l’Activity avec :

java
registerReceiver(airplaneReceiver, filter);


* Écoute :

java
Intent.ACTION_AIRPLANE_MODE_CHANGED


* Affiche un Toast lorsque le mode avion change



###Receiver Statique (BOOT_COMPLETED)

* Déclaré dans le Manifest
* Déclenché au démarrage du téléphone
* Fonctionne même si l’application est fermée



### Broadcast Personnalisé

* Envoyé depuis `MainActivity` :

java
Intent intent = new Intent("com.example.receiverdemo.CUSTOM_EVENT");
intent.putExtra("message", "Bonjour depuis le broadcast !");
sendBroadcast(intent);


* Reçu par `CustomEventReceiver`



##Permissions

Ajouter dans `AndroidManifest.xml` :

xml
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>




##Configuration du Manifest

`xml
<application ...>

    <receiver
        android:name=".BootReceiver"
        android:exported="false">
        <intent-filter>
            <action android:name="android.intent.action.BOOT_COMPLETED"/>
        </intent-filter>
    </receiver>

    <receiver
        android:name=".CustomEventReceiver"
        android:exported="false"/>

</application>




##nterface

* TextView → affiche le statut
* Bouton → activer/désactiver le receiver du mode avion
* Bouton → envoyer un broadcast personnalisé



##Exécution


* Ouvrir avec Android Studio
* Lancer sur émulateur ou téléphone


