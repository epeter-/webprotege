package edu.stanford.bmir.protege.web.shared.crud;

import edu.stanford.bmir.protege.web.shared.HasLexicalForm;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 08/08/2013
 */
public class EntityCrudKitId implements Serializable, HasLexicalForm {

    private String lexicalForm;

    /**
     * For serialization purposes only.
     */
    private EntityCrudKitId() {
    }

    /**
     * Constructs an instance of {@link EntityCrudKitId} for the specified lexical form.
     * @param lexicalForm The lexical form of the id.  Not {@code null}.
     * @throws NullPointerException if {@code lexicalForm} is {@code null}.
     */
    private EntityCrudKitId(String lexicalForm) {
        this.lexicalForm = checkNotNull(lexicalForm);
    }

    /**
     * Gets an instances if {@link EntityCrudKitId} for the specified lexical form.
     * @param lexicalForm The id lexical form.  Not {@code null}.
     * @return The id for the specified lexical form. Not {@code null}.
     * @throws NullPointerException if {@code lexicalForm} is {@code null}.
     */
    public static EntityCrudKitId get(String lexicalForm) {
        return new EntityCrudKitId(lexicalForm);
    }

    /**
     * Gets the lexical form for this id.
     * @return The lexical form of this id.  Not {@code null}.
     */
    @Override
    public String getLexicalForm() {
        return lexicalForm;
    }

    
}
