package family

import scala.collection.mutable.ListBuffer

class Family(val name: String) {

	val ageOfAdulthood = 18
	val members = ListBuffer[FamilyMember]()

	/**
	 * Add a member to this family.
	 * 
	 * @param member The FamilyMember to add to this family.
	 */
	def add(member: FamilyMember): Family = {
		members += member
		this
	}

	/** Return the number of humans in this family. */
	def size(): Int = {
		// TIP: implement using the count() method defined in the Seq[A] trait,
		// which takes in a predicate function and uses it to determine which
		// items to include in the count:
		// 
		// count(p: (A) ⇒ Boolean): Int
		// 
		members.count(_.isInstanceOf[Person])
	}
	
	/** Return the pets of this family. */
	def pets(): Seq[FamilyMember] = {
		// TIP: implement using the filter() or filterNot() methods defined in 
		// the Seq[A] trait, which takes in a predicate function and uses it to 
		// determine which items to include in the returned sequence:
		// 
		// filter(p: (A) ⇒ Boolean): Seq[A]
		// filterNot(p: (A) ⇒ Boolean): Seq[A]
		// 
		members.filterNot(_.isInstanceOf[Person])
	}
	
	/** Return the adults in this family. */
	def adults(): Seq[Person] = {
		// TIP: Implement using the filter() or filterNot() methods defined in 
		// the Seq[A] trait, which takes in a predicate function and uses it to 
		// determine which items to include in the returned sequence:
		// 
		// filter(p: (A) ⇒ Boolean): Seq[A]
		// filterNot(p: (A) ⇒ Boolean): Seq[A]
		// 
		humans().filter(_.age >= ageOfAdulthood)
	}
	
	/** Return the children in this family. */
	def children(): Seq[Person] = {
		// TIP: Implement using the filter() or filterNot() methods defined in 
		// the Seq[A] trait, which takes in a predicate function and uses it to 
		// determine which items to include in the returned sequence:
		// 
		// filter(p: (A) ⇒ Boolean): Seq[A]
		// filterNot(p: (A) ⇒ Boolean): Seq[A]
		// 
		humans().filter(_.age < ageOfAdulthood)
	}
	
	/** Return the sum of ages of all humans in this family. */
	def age(): Int = {
		// TIP: The easy way out is to loop through the humans' ages using
		// e.g. map() and a mutable variable to hold the sum. It's not very
		// idiomatic Scala, though, and doesn't make use of the superpowers
		// of the Scala Collection API...
		// 
		// map[B](f: (A) ⇒ B): Seq[B]
		// foreach(f: (A) ⇒ Unit): Unit
		// 
		// PROTIP: If you're feeling adventurous, reach for a more functional
		// style solution and use foldLeft() to turn this into a one-liner:
		// 
		// foldLeft[B](z: B)(op: (B, A) ⇒ B): B
		
		var sum = 0
		humans().map(_.age).foreach((age) => sum += age)
		sum
		
		// or, the more functional style:
		humans().map(_.age).foldLeft(0) ((sum:Int, age:Int) => sum + age)
	}

	/**
	 * Returns a plain English description of who are in the family.
	 * 
	 * The ladies should be listed first and the men last. Within each
	 * gender we want the most senior family member named first, i.e. 
	 * mom before daughter, dad before son, and so forth.
	 */
	override def toString: String = {
		// TIP: Sort the family members with sortBy()
		// 
		// TIP: mkString(sep:String) is good for joining a string but you might
		// need dropRight() and last() as well to get that 'and' in the right place.
		
		// (We'll provide this for a starting point, introducing mkString() and format():)
		// val memberNames = humans.map(_.name)
		// val namesString = names.mkString(", ")
		// "%s are %s".format(name, namesString)

		// Sort the family members with sortBy():
		val memberNames = humans.sortBy(x => (x.gender.toString, -x.age)).map(_.name)

		// Getting the ' and ' in place with dropRight() and last():
		val firstNames = memberNames.dropRight(1).mkString(", ")
		val namesString = "%s and %s".format(firstNames, memberNames.last)
		
		"%s are %s".format(name, namesString)
	}
	
	private def humans(): Seq[Person] = {
		// TODO: return a list of Person instances that belong to this family
		val people = members.filter(_.isInstanceOf[Person])
		people.map(_.asInstanceOf[Person])
	}
}