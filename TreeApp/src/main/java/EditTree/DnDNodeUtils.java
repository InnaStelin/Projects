package EditTree;

 interface DnDNodeUtils {
  boolean canAddChild(DnDNode node);
    int getAddIndex(DnDNode node);
    boolean equals(Object o);
    boolean equalsNodeOnly(DnDNode node);
}
