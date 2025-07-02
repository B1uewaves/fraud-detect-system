import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

df = pd.read_csv('backend-java/src/main/resources/mobileMoneyTransactions.csv')

# print(df.head())
# print(df['type'].value_counts()) 
# print(df['isFraud'].value_counts(normalize=True))
# fraud = df[df['isFraud'] == 1]
# legit = df[df['isFraud'] == 0]

# print(fraud['amount'].describe())
# print(legit['amount'].describe())
# Keep only relevant numerical columns

# numerical_cols = ['step', 'amount', 'oldbalanceOrg', 'newbalanceOrig', 
#                   'oldbalanceDest', 'newbalanceDest', 'isFraud', 'isFlaggedFraud']

# # Compute correlation matrix
# corr_matrix = df[numerical_cols].corr()
# plt.figure(figsize=(10, 8))
# sns.heatmap(corr_matrix, annot=True, cmap='coolwarm')
# plt.show()

contingency_table = pd.crosstab(df['type'], df['isFraud'])
fraud_proportions = df.groupby('type')['isFraud'].mean().reset_index()
fraud_proportions.columns = ['type', 'Fraud_Proportion']
print("\nFraud Proportion by Type:")
print(fraud_proportions)