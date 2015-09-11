package com.gman.idea.plugin.concordion.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMember;
import com.intellij.psi.PsiReference;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConcordionReference<T extends PsiMember> implements PsiReference {

    private final PsiElement owner;
    private final T referent;
    private final TextRange range;

    public ConcordionReference(@NotNull PsiElement owner, @NotNull T referent) {
        this.owner = owner;
        this.referent = referent;
        this.range = createTextRange(owner, referent);
    }

    @Override
    public PsiElement getElement() {
        return owner;
    }

    @Override
    public TextRange getRangeInElement() {
        return range;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return referent;
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return "???";
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        return null;
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        return owner;
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
        return referent.equals(element);
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

    private TextRange createTextRange(@NotNull PsiElement owner, @NotNull T referent) {
        if (referent.getName() != null) {
            return new TextRange(0, referent.getName().length());
        } else {
            return new TextRange(0, owner.getTextLength());
        }
    }
}