#ifndef NODE_H
#define NODE_H

#include <unordered_set>
#include <vector>

#include "mytypes.hpp"

class Node {
    public:
        static std::unordered_set<int> attributesAlreadyUsedToSplitANode;
        Node(my::features*, my::classes*);
        void setIndexOfFeatureToUseToSplitSamplesUp(int);
        int getIndexOfFeatureToUseToSplitSamplesUp();
        my::features* getFeatures();
        my::classes* getClasses();
        void setParent(Node*);
        Node* getParent();
        void setLabel(int);
        int getLabel();
        std::vector<Node*> getChildren();
        void setChildren(const std::vector<Node*>&);
        bool doIncludedSamplesAllHaveSameClass();

    private:
        static const int NO_LABEL_ASSIGNED = -1;
        static const int NO_INDEX_ASSIGNED = -1;

        std::vector<Node*> children;
        int label;
        int indexOfFeatureToUseToSplitSamplesUp;
        Node* parent;
        my::features* features;
        my::classes* classes;
};

#endif