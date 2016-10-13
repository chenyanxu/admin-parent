package com.kalix.admin.audit.biz;


import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;

/**
 * Created by sunlf on 2015/8/27.
 */
public class AuditUtil {
    public static String Match(final Object oldObj, final Object newObj, String entityClassName) {
        String[] str = entityClassName.split("\\.");
        int size = str.length;
        final String className = str[size - 1];
        DiffNode diff = ObjectDifferBuilder.buildDefault().compare(newObj, oldObj);
        final StringBuilder stringBuilder = new StringBuilder();
        diff.visit(new DiffNode.Visitor() {
            public void node(DiffNode node, Visit visit) {
                final Object baseValue = node.canonicalGet(oldObj);
                final Object workingValue = node.canonicalGet(newObj);

                if (!node.isRootNode()) {
                    /*if (!node.getPropertyName().equals("version") && !node.getPropertyName().equals("creationDate") && !node.getPropertyName().equals("createBy")
                            && !node.getPropertyName().equals("updateBy") && !node.getPropertyName().equals("updateDate"))*/
                        stringBuilder.append("字段名:[" + node.getPropertyName() + "],旧值[" +
                                baseValue + "],新值[" + workingValue + "];");
                }

            }
        });
        return className + "{" + stringBuilder.toString() + "}";
    }
}
