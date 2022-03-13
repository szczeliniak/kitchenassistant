package pl.szczeliniak.kitchenassistant.receipt

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.szczeliniak.kitchenassistant.receipt.commands.*
import pl.szczeliniak.kitchenassistant.receipt.commands.factories.IngredientFactory
import pl.szczeliniak.kitchenassistant.receipt.commands.factories.ReceiptFactory
import pl.szczeliniak.kitchenassistant.receipt.commands.factories.StepFactory
import pl.szczeliniak.kitchenassistant.receipt.queries.GetReceiptQuery
import pl.szczeliniak.kitchenassistant.receipt.queries.GetReceiptsQuery
import pl.szczeliniak.kitchenassistant.user.queries.GetUserQuery

@Configuration
class ReceiptConfiguration {

    @Bean
    fun getReceiptQuery(receiptDao: ReceiptDao): GetReceiptQuery = GetReceiptQuery(receiptDao)

    @Bean
    fun getReceiptsQuery(receiptDao: ReceiptDao): GetReceiptsQuery = GetReceiptsQuery(receiptDao)

    @Bean
    fun addReceiptCommand(receiptDao: ReceiptDao, receiptFactory: ReceiptFactory): AddReceiptCommand =
        AddReceiptCommand(receiptDao, receiptFactory)

    @Bean
    fun addIngredientCommand(receiptDao: ReceiptDao, ingredientFactory: IngredientFactory): AddIngredientCommand =
        AddIngredientCommand(receiptDao, ingredientFactory)

    @Bean
    fun addStepCommand(receiptDao: ReceiptDao, stepFactory: StepFactory): AddStepCommand =
        AddStepCommand(receiptDao, stepFactory)
    
    @Bean
    fun deleteReceiptCommand(receiptDao: ReceiptDao): DeleteReceiptCommand = DeleteReceiptCommand(receiptDao)

    @Bean
    fun deleteStepCommand(receiptDao: ReceiptDao): DeleteStepCommand = DeleteStepCommand(receiptDao)

    @Bean
    fun deleteIngredientCommand(receiptDao: ReceiptDao): DeleteIngredientCommand = DeleteIngredientCommand(receiptDao)

    @Bean
    fun stepFactory(): StepFactory = StepFactory()

    @Bean
    fun ingredientFactory(): IngredientFactory = IngredientFactory()

    @Bean
    fun receiptFactory(
        getUserQuery: GetUserQuery,
        stepFactory: StepFactory,
        ingredientFactory: IngredientFactory
    ): ReceiptFactory = ReceiptFactory(getUserQuery, ingredientFactory, stepFactory)

}