package dev.warrior.spring.mvc.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
    @Override
    public Long convertToDatabaseColumn(Money attribute) {
        return attribute == null ? null : attribute.getAmountMinorLong();
    }

    @Override
    public Money convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : Money.ofMinor(CurrencyUnit.of("CNY"), dbData);
    }
}
