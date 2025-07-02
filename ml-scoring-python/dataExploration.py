import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

df = pd.read_csv('backend-java/src/main/resources/mobileMoneyTransactions.csv')

print(df.head())  