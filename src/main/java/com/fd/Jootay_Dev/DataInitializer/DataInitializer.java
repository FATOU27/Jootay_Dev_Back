package com.fd.Jootay_Dev.DataInitializer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fd.Jootay_Dev.enums.Difficulty;
import com.fd.Jootay_Dev.enums.QuestionCategory;
import com.fd.Jootay_Dev.model.Question;
import com.fd.Jootay_Dev.reporitory.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

        private final QuestionRepository questionRepository;

        @Override
        public void run(String... args) {
                System.out.println("🔍 DataInitializer démarré — count = " + questionRepository.count());

                if (questionRepository.count() > 0) {
                        System.out.println("⚠️ Table non vide, initialisation ignorée.");
                        return;
                }

                try {
                        questionRepository.saveAll(List.of(

                                        Question.builder()
                                                        .title("Qu'est-ce que le overriding d'une méthode en Java ?")
                                                        .answer("Le method overriding (redéfinition) consiste à fournir une nouvelle implémentation "
                                                                        +
                                                                        "d'une méthode héritée d'une classe parente. La méthode dans la sous-classe doit avoir "
                                                                        +
                                                                        "la même signature (nom + paramètres) et le même type de retour. "
                                                                        +
                                                                        "On utilise l'annotation @Override pour indiquer explicitement qu'on redéfinit une méthode, "
                                                                        +
                                                                        "ce qui permet au compilateur de détecter les erreurs si la signature ne correspond pas.")
                                                        .codeExample("""
                                                                        class Animal {
                                                                            void parler() {
                                                                                System.out.println("...");
                                                                            }
                                                                        }
                                                                        class Chien extends Animal {
                                                                            @Override
                                                                            void parler() {
                                                                                System.out.println("Woof !");
                                                                            }
                                                                        }
                                                                        Animal a = new Chien();
                                                                        a.parler(); // Affiche "Woof !"
                                                                        """)
                                                        .difficulty(Difficulty.JUNIOR)
                                                        .category(QuestionCategory.POO)
                                                        .build(),

                                        Question.builder()
                                                        .title("Que signifie le mot-clé 'super' en Java ?")
                                                        .answer("Le mot-clé 'super' fait référence à la classe parente directe. Il a trois usages : "
                                                                        +
                                                                        "1) super() appelle le constructeur de la classe parente. "
                                                                        +
                                                                        "2) super.methode() appelle une méthode de la classe parente redéfinie. "
                                                                        +
                                                                        "3) super.attribut accède à un attribut masqué de la classe parente.")
                                                        .codeExample("""
                                                                        class Animal {
                                                                            String nom;
                                                                            Animal(String nom) { this.nom = nom; }
                                                                        }
                                                                        class Chien extends Animal {
                                                                            Chien(String nom) {
                                                                                super(nom); // appel constructeur parent
                                                                            }
                                                                        }
                                                                        """)
                                                        .difficulty(Difficulty.JUNIOR)
                                                        .category(QuestionCategory.POO)
                                                        .build(),

                                        Question.builder()
                                                        .title("Quelle est la différence entre Overloading et Overriding en Java ?")
                                                        .answer("L'Overloading (surcharge) : plusieurs méthodes avec le même nom mais des paramètres différents dans la MÊME classe. "
                                                                        +
                                                                        "Résolution à la compilation. " +
                                                                        "L'Overriding (redéfinition) : redéfinir une méthode héritée dans une SOUS-classe avec la même signature. "
                                                                        +
                                                                        "Résolution à l'exécution (polymorphisme dynamique).")
                                                        .codeExample("""
                                                                        // Overloading
                                                                        class Calc {
                                                                            int add(int a, int b) { return a + b; }
                                                                            double add(double a, double b) { return a + b; }
                                                                        }
                                                                        // Overriding
                                                                        class Animal { void parler() { System.out.println("..."); } }
                                                                        class Chat extends Animal {
                                                                            @Override
                                                                            void parler() { System.out.println("Miaou !"); }
                                                                        }
                                                                        """)
                                                        .difficulty(Difficulty.MID)
                                                        .category(QuestionCategory.POO)
                                                        .build(),

                                        Question.builder()
                                                        .title("Quelle est la différence entre une classe abstraite et une interface ?")
                                                        .answer("Classe abstraite : peut avoir des méthodes avec ou sans implémentation, des attributs d'instance et des constructeurs. "
                                                                        +
                                                                        "Une classe ne peut hériter que d'une seule classe abstraite. "
                                                                        +
                                                                        "Interface : depuis Java 8, peut avoir des méthodes default/static. Pas d'attributs d'instance. "
                                                                        +
                                                                        "Une classe peut implémenter plusieurs interfaces.")
                                                        .codeExample("""
                                                                        abstract class Forme {
                                                                            String couleur;
                                                                            abstract double aire();
                                                                            void afficher() { System.out.println(couleur); }
                                                                        }
                                                                        interface Dessinable {
                                                                            void dessiner();
                                                                            default void effacer() { System.out.println("Effacé"); }
                                                                        }
                                                                        class Cercle extends Forme implements Dessinable {
                                                                            double rayon;
                                                                            public double aire() { return Math.PI * rayon * rayon; }
                                                                            public void dessiner() { System.out.println("O"); }
                                                                        }
                                                                        """)
                                                        .difficulty(Difficulty.MID)
                                                        .category(QuestionCategory.POO)
                                                        .build(),

                                        Question.builder()
                                                        .title("Quelle est la différence entre == et .equals() en Java ?")
                                                        .answer("== compare les références mémoire (adresses), tandis que .equals() compare le contenu des objets. "
                                                                        +
                                                                        "Pour les String, il faut toujours utiliser .equals().")
                                                        .codeExample("""
                                                                        String a = new String("hello");
                                                                        String b = new String("hello");
                                                                        System.out.println(a == b);       // false
                                                                        System.out.println(a.equals(b));  // true
                                                                        """)
                                                        .difficulty(Difficulty.JUNIOR)
                                                        .category(QuestionCategory.POO)
                                                        .build(),

                                        Question.builder()
                                                        .title("Qu'est-ce que le polymorphisme en Java ?")
                                                        .answer("Le polymorphisme permet à un objet de prendre plusieurs formes. "
                                                                        +
                                                                        "Le polymorphisme dynamique permet d'appeler la méthode de la classe réelle de l'objet "
                                                                        +
                                                                        "même si la référence est déclarée avec le type parent.")
                                                        .codeExample("""
                                                                        class Animal { void parler() { System.out.println("..."); } }
                                                                        class Chien extends Animal {
                                                                            @Override
                                                                            void parler() { System.out.println("Woof!"); }
                                                                        }
                                                                        Animal a = new Chien();
                                                                        a.parler(); // Affiche "Woof!"
                                                                        """)
                                                        .difficulty(Difficulty.JUNIOR)
                                                        .category(QuestionCategory.POO)
                                                        .build(),

                                        Question.builder()
                                                        .title("Quelle est la différence entre ArrayList et LinkedList ?")
                                                        .answer("ArrayList utilise un tableau dynamique : accès par index en O(1), insertion en milieu en O(n). "
                                                                        +
                                                                        "LinkedList utilise une liste doublement chaînée : insertion en O(1), accès par index en O(n). "
                                                                        +
                                                                        "ArrayList est généralement plus performant grâce à la localité mémoire.")
                                                        .difficulty(Difficulty.JUNIOR)
                                                        .category(QuestionCategory.COLLECTIONS)
                                                        .build(),

                                        Question.builder()
                                                        .title("Comment fonctionne HashMap en Java ?")
                                                        .answer("HashMap utilise un tableau de buckets. Le hashCode() de la clé détermine le bucket. "
                                                                        +
                                                                        "En cas de collision, les éléments sont stockés en liste chaînée ou arbre rouge-noir (Java 8+). "
                                                                        +
                                                                        "Complexité moyenne O(1) pour get/put.")
                                                        .codeExample("""
                                                                        Map<String, Integer> map = new HashMap<>();
                                                                        map.put("Java", 17);
                                                                        map.get("Java"); // O(1) en moyenne
                                                                        """)
                                                        .difficulty(Difficulty.MID)
                                                        .category(QuestionCategory.COLLECTIONS)
                                                        .build(),

                                        Question.builder()
                                                        .title("À quoi sert Optional en Java 8 ?")
                                                        .answer("Optional est un conteneur qui peut contenir ou non une valeur non-nulle. "
                                                                        +
                                                                        "Il permet d'éviter les NullPointerException en forçant la gestion explicite de l'absence de valeur.")
                                                        .codeExample("""
                                                                        Optional<String> nom = Optional.of("Alice");
                                                                        String resultat = nom
                                                                            .filter(s -> s.length() > 3)
                                                                            .map(String::toUpperCase)
                                                                            .orElse("Inconnu");
                                                                        System.out.println(resultat); // ALICE
                                                                        """)
                                                        .difficulty(Difficulty.MID)
                                                        .category(QuestionCategory.JAVA8_PLUS)
                                                        .build(),

                                        Question.builder()
                                                        .title("Comment fonctionne le Garbage Collector en Java ?")
                                                        .answer("Le GC libère automatiquement la mémoire des objets non référencés. "
                                                                        +
                                                                        "Il divise le heap en Young Generation (Eden + Survivor) et Old Generation. "
                                                                        +
                                                                        "Minor GC pour les jeunes objets (rapide), Major/Full GC pour les vieux (lent). "
                                                                        +
                                                                        "Implémentations : G1 (défaut Java 9+), ZGC, Shenandoah.")
                                                        .difficulty(Difficulty.SENIOR)
                                                        .category(QuestionCategory.JVM)
                                                        .build()));

                        System.out.println("✅ Questions Java initialisées !");

                } catch (Exception e) {
                        System.err.println("❌ Erreur DataInitializer : " + e.getMessage());
                        e.printStackTrace();
                }
        }
}