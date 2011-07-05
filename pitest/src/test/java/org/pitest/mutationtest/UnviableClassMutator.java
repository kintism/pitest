/*
 * Copyright 2010 Henry Coles
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package org.pitest.mutationtest;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.Context;
import org.pitest.mutationtest.engine.gregor.InsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

public class UnviableClassMutator implements MethodMutatorFactory {

  public MethodVisitor create(final Context context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new UnviableClassMethodVisitor(this, methodInfo, context,
        methodVisitor);
  }

  public String getGloballyUniqueId() {
    return this.getClass().getName();
  }

}

class UnviableClassMethodVisitor extends InsnMutator {

  public UnviableClassMethodVisitor(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final Context context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    final Map<Integer, ZeroOperandMutation> map = new HashMap<Integer, ZeroOperandMutation>();
    // map.put(Opcodes.ALOAD, new InsnSubstitution(Opcodes.TABLESWITCH,
    // "Made unviable class"));
    map.put(Opcodes.IRETURN, new InsnSubstitution(Opcodes.FCMPG,
        "Made unviable class"));
    map.put(Opcodes.RETURN, new InsnSubstitution(Opcodes.FCMPG,
        "Made unviable class"));
    return map;
  }

}