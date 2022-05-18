# -*- encoding: utf-8 -*-
# stub: gherkin-ruby 0.3.2 ruby lib

Gem::Specification.new do |s|
  s.name = "gherkin-ruby".freeze
  s.version = "0.3.2"

  s.required_rubygems_version = Gem::Requirement.new(">= 0".freeze) if s.respond_to? :required_rubygems_version=
  s.require_paths = ["lib".freeze]
  s.authors = ["Marc Divins".freeze, "Josep M. Bach".freeze]
  s.date = "2014-07-01"
  s.description = "Gherkin-ruby is a Gherkin parser in pure Ruby using Rexical and Racc".freeze
  s.email = ["marcdivc@gmail.com".freeze, "josep.m.bach@gmail.com".freeze]
  s.homepage = "http://github.com/codegram/gherkin-ruby".freeze
  s.rubygems_version = "3.0.3.1".freeze
  s.summary = "Gherkin-ruby is a Gherkin parser in pure Ruby using Rexical and Racc".freeze

  s.installed_by_version = "3.0.3.1" if s.respond_to? :installed_by_version

  if s.respond_to? :specification_version then
    s.specification_version = 4

    if Gem::Version.new(Gem::VERSION) >= Gem::Version.new('1.2.0') then
      s.add_development_dependency(%q<minitest>.freeze, [">= 0"])
      s.add_development_dependency(%q<rexical>.freeze, [">= 0"])
    else
      s.add_dependency(%q<minitest>.freeze, [">= 0"])
      s.add_dependency(%q<rexical>.freeze, [">= 0"])
    end
  else
    s.add_dependency(%q<minitest>.freeze, [">= 0"])
    s.add_dependency(%q<rexical>.freeze, [">= 0"])
  end
end
