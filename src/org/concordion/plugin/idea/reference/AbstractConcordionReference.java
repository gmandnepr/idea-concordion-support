package org.concordion.plugin.idea.reference;

import org.concordion.plugin.idea.lang.psi.ConcordionPsiElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractConcordionReference<T extends ConcordionPsiElement> implements PsiReference {

    protected final T owner;
    protected final TextRange range;

    public AbstractConcordionReference(@NotNull T owner, @NotNull TextRange range) {
        this.owner = owner;
        this.range = range;
    }

    @Override
    public PsiElement getElement() {
        return owner;
    }

    @Override
    public TextRange getRangeInElement() {
        return range;
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return owner.getText();
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        owner.setName(newElementName);
        return owner;
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        return owner;
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
        return element.equals(resolve());
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }

    @Override
    public boolean isSoft() {
        return false;
    }
}
