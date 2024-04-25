# How to use it in your project

[![](https://jitpack.io/v/torrespro/AutoQuality-Playground.svg)](https://jitpack.io/#torrespro/AutoQuality-Playground)

How to get a Git project into your build:

## Maven

Step 1. Add the JitPack repository to your build file


	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.torrespro.AutoQuality-Playground</groupId>
	    <artifactId>error-prone-contrib</artifactId>
	    <version>1.0.0</version>
	</dependency>

## Gradle

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.torrespro.AutoQuality-Playground:error-prone-contrib:1.0.0'
	}