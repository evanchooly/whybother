* Intro
* What is Kotlin?
	* Statically typed
	* multiparadigm
	* journeyman's language
	* nothing exotic or revolutionary
	* combines known practices
* Do I use it?
	* every project now
	* infrastructure code at work
* Why bother?
	* functional
		* cleaner than java's
	* Nullability
		* elvis
		* let	
	* Java interop
		* use any JVM library you wish
		* some libs are better than others
			* Scala easily leaks its scala-ness out of the api
		* annotations to adjust bytecode to be more natural to java users
			* @JvmName
				* top level functions	
			* @JvmStatic  (OverloadsTest)
			* @JvmOverloads  (OverloadsTest)	
		* http://kotlinlang.org/docs/reference/java-to-kotlin-interop.html
	* Delegation
		* lazy
		* Delegates
		* composition
			* Listogram
	* DSLs
		* bottlerocket
	* coroutines
	* Kotlin-js
	* Kotlin-native
		* CsvParser
