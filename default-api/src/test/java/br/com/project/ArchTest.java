package br.com.project;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import org.junit.Test;

public class ArchTest {

    private static final String PACKAGE = "br.com.project";
    private JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages(PACKAGE);

    @Test
    public void should_validate_adapter_package_naming() {
        ArchRule rule = ArchRuleDefinition.classes().that().haveSimpleNameEndingWith("Adapter").should()
                .resideInAPackage(PACKAGE + ".adapter");
        rule.check(importedClasses);
    }

    @Test
    public void should_validate_controller_package_naming() {
        ArchRule rule = ArchRuleDefinition.classes().that().haveSimpleNameEndingWith("Controller").should()
                .resideInAPackage(PACKAGE + ".resource");
        rule.check(importedClasses);
    }

    @Test
    public void should_validate_repository_package_naming() {
        ArchRule rule = ArchRuleDefinition.classes().that().haveSimpleNameEndingWith("Repository").should()
                .resideInAPackage(PACKAGE + ".repository");
        rule.check(importedClasses);
    }

    @Test
    public void should_validate_service_package_naming() {
        ArchRule rule = ArchRuleDefinition.classes().that().haveSimpleNameEndingWith("Service").should()
                .resideInAPackage(PACKAGE + ".service");
        rule.check(importedClasses);
    }

    @Test
    public void should_validate_layered_architecture() {
        Architectures.LayeredArchitecture layeredArchitecture = Architectures.layeredArchitecture()
                .layer("Controller").definedBy("..resource..")
                .layer("Service").definedBy("..service..")
                .layer("Repository").definedBy("..repository..")
                .layer("Entity").definedBy("..entity..")
                .layer("DTO").definedBy("..domain..")
                .layer("Adapter").definedBy("..adapter..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
                .whereLayer("Entity").mayOnlyBeAccessedByLayers("Controller", "Repository", "Service", "Adapter")
                .whereLayer("DTO").mayOnlyBeAccessedByLayers("Repository", "Service", "Controller", "Adapter");
        layeredArchitecture.check(importedClasses);
    }
}
