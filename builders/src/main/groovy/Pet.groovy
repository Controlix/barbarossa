import groovy.transform.Canonical
import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Canonical
@ToString(includeSuper=true, includeNames=true)
@Builder(builderStrategy = SimpleStrategy)
class Pet extends Animal {

    String name
}


