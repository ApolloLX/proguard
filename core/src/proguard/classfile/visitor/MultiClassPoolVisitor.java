/*
 * ProGuard -- shrinking, optimization, obfuscation, and preverification
 *             of Java bytecode.
 *
 * Copyright (c) 2002-2019 Guardsquare NV
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package proguard.classfile.visitor;

import proguard.classfile.ClassPool;
import proguard.util.ArrayUtil;


/**
 * This ClassPoolVisitor delegates all visits to each ClassPoolVisitor
 * in a given list.
 *
 * @author Eric Lafortune
 */
public class MultiClassPoolVisitor implements ClassPoolVisitor
{
    private ClassPoolVisitor[] classPoolVisitors;
    private int                classPoolVisitorCount;


    public MultiClassPoolVisitor()
    {
        this.classPoolVisitors = new ClassPoolVisitor[16];
    }


    public MultiClassPoolVisitor(ClassPoolVisitor... classPoolVisitors)
    {
        this.classPoolVisitors     = classPoolVisitors;
        this.classPoolVisitorCount = classPoolVisitors.length;
    }


    public void addClassPoolVisitor(ClassPoolVisitor classPoolVisitor)
    {
        classPoolVisitors =
            ArrayUtil.add(classPoolVisitors,
                          classPoolVisitorCount++,
                          classPoolVisitor);
    }


    // Implementations for ClassPoolVisitor.

    public void visitClassPool(ClassPool classPool)
    {
        for (int index = 0; index < classPoolVisitorCount; index++)
        {
            classPoolVisitors[index].visitClassPool(classPool);
        }
    }
}
